package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.domain.interfaces.UserService;
import com.pet.hotel.businessLogic.domain.transferObjects.*;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import com.pet.hotel.businessLogic.domain.transferObjects.user.HistoryRentingResponse;
import com.pet.hotel.businessLogic.domain.transferObjects.user.UserDto;
import com.pet.hotel.businessLogic.mappers.Mapper;
import com.pet.hotel.businessLogic.utils.EmailSender;
import com.pet.hotel.businessLogic.utils.PhotoParser;
import com.pet.hotel.businessLogic.utils.Validation;
import com.pet.hotel.data.database.entities.PetEntity;
import com.pet.hotel.data.database.entities.ScheduleEntity;
import com.pet.hotel.data.database.entities.UserEntity;
import com.pet.hotel.data.database.repositories.*;
import com.pet.hotel.data.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private Mapper mapper;

    @Override
    @Async
    public UserDto getUserById(Integer userId) {
        if (userRepository.existsById(userId)) {
            return mapper.map(userRepository.findById(userId), UserDto.class);
        } else {
            return null;
        }
    }

    @Override
    @Async
    public boolean saveUser(UserDto user, MultipartFile file) {
        if (user == null) return false;
        if (!Validation.isPasswordCorrect(user.getPasswordHash())) return false;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        if (file != null) {
            PhotoParser photoParser = new PhotoParser(file);
            photoParser.savePhoto();
            user.setPhotoLink(photoParser.getDropboxFilePath());
        }

        userRepository.save(mapper.map(user, UserEntity.class));
        return true;
    }

    @Override
    @Async
    public byte[] getUserPhoto(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            return PhotoParser.pullPhoto(userRepository.findById(userId).get().getPhotoLink());
        }
        return null;
    }

    @Override
    @Async
    public boolean deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    @Async
    public UserDto findUserByEmail(String email) {
        if (email == null || email.isEmpty()) return null;

        return mapper.map(userRepository.findUserByEmail(email), UserDto.class);
    }

    @Override
    @Async
    public List<PetDto> getListOfPets(int userId) {
        if (userRepository.existsById(userId) && petRepository.getPetsByUserId(userId).isPresent()) {
            return mapper.map(petRepository.getPetsByUserId(userId).get(), PetDto.class);
        }
        return null;
    }

    @Override
    @Async
    public boolean addPets(List<PetDto> pets) {
        if (!pets.isEmpty()) {
            petRepository.saveAll(mapper.map(pets, PetEntity.class));
            return true;
        }
        return false;
    }

    @Override
    @Async
    public List<HistoryRentingResponse> getHistoryRenting(int userId) {
        if (userRepository.existsById(userId) && userRepository.getHistoryOfRentingByUserId(userId).isPresent()) {
           return mapper.map(userRepository.getHistoryOfRentingByUserId(userId).get(), HistoryRentingResponse.class);
        }
        return null;
    }

    @Override
    @Async
    public List<HotelDto> getAllHotels() {
        return mapper.map(hotelRepository.findAll(), HotelDto.class);
    }

    @Override
    @Async
    public List<RoomDto> getAllRoomsByHotel(int hotelId) {
        if (hotelRepository.existsById(hotelId) && roomRepository.getAllRoomsByHotelId(hotelId).isPresent()) {
            return mapper.map(roomRepository.getAllRoomsByHotelId(hotelId).get(), RoomDto.class);
        }
        return null;
    }

    @Override
    @Async
    public boolean addNewRentingForUser(List<ScheduleDto> scheduleDtoList) {
        try {
            if (isRentingListValid(scheduleDtoList)) {
                scheduleRepository.saveAll(mapper.map(scheduleDtoList, ScheduleEntity.class));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Async
    private boolean isRentingListValid(List<ScheduleDto> scheduleDtos) {
        ScheduleDto scheduleDto;
        for (ScheduleDto dto : scheduleDtos) {
            scheduleDto = dto;
            if (scheduleDtos.isEmpty() || !scheduleDto.getDateTime().isAfter(LocalDateTime.now()) ||
                    !scheduleDto.getDietDto().getRentDto().getBeginDate().isAfter(LocalDateTime.now()) ||
                    !scheduleDto.getDietDto().getRentDto().getEndDate().isAfter(scheduleDto.getDietDto().getRentDto().getBeginDate())) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Async
    public List<RoomDto> getAllFreeRoomByPeriod(int hotelId, LocalDateTime beginDate, LocalDateTime endDate) {
        if (hotelRepository.existsById(hotelId) && beginDate.isAfter(LocalDateTime.now()) && endDate.isAfter(LocalDateTime.now()) &&
                roomRepository.getAllFreeRoomByPeriod(hotelId, beginDate, endDate).isPresent()) {
            return mapper.map(roomRepository.getAllFreeRoomByPeriod(hotelId, beginDate, endDate).get(), RoomDto.class);
        }
        return null;
    }

    @Override
    @Async
    public boolean sendMessageToAdmin(int userId, String message) {
        if (userRepository.existsById(userId)) {
            Stream<UserEntity> userBusinessAdmin = userRepository.findAll().stream().filter(userEntity -> userEntity.getTypeUser().toString().equals(UserType.BUSINESS_ADMIN.toString()));
            EmailSender emailSender = new EmailSender();
            emailSender.sendEmail(userBusinessAdmin.toList().get(0).getEmail(), message);
            return true;
        }

        return false;
    }
}
