package com.pet.hotel.services;


import com.pet.hotel.businessLogic.domain.transferObjects.RoomDto;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomRequest;
import com.pet.hotel.businessLogic.domain.transferObjects.StateOfRoomResponse;
import com.pet.hotel.businessLogic.mappers.Mapper;
import com.pet.hotel.businessLogic.services.DeviceServiceImpl;
import com.pet.hotel.data.database.entities.RoomEntity;
import com.pet.hotel.data.database.entities.UserEntity;
import com.pet.hotel.data.database.repositories.RentEntity;
import com.pet.hotel.data.database.repositories.RoomRepository;
import com.pet.hotel.data.database.repositories.UserRepository;
import com.pet.hotel.data.enums.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class DeviceServiceFunctionalTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RentEntity rentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Autowired
    private DeviceServiceImpl deviceFunctionalService;

    @Test
    public void testGetStateOfRoomSuccess() {
        // Arrange
        int roomId = 1;
        String ipAddress = "127.0.0.1";
        StateOfRoomResponse expectedResponse = new StateOfRoomResponse();

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomId(roomId);
        roomEntity.setIp(ipAddress);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Act
        StateOfRoomResponse actualResponse = deviceService.getStateOfRoom(roomId);

        // Assert
        assertNull(actualResponse);
    }

    @Test
    public void testSetNewStateOfRoomSuccess() {
        // Arrange
        int roomId = 1;
        String ipAddress = "127.0.0.1";
        StateOfRoomResponse stateOfRoomResponse = new StateOfRoomResponse();

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomId(roomId);
        roomEntity.setIp(ipAddress);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Act
        boolean result = deviceService.setNewStateOfRoom(roomId, stateOfRoomResponse);

        // Assert
        assertTrue(result);
    }

    private HttpEntity<StateOfRoomResponse> createRequestEntity(StateOfRoomResponse stateOfRoomResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(stateOfRoomResponse, headers);
    }

    @Test
    void testUpdateStateOfRoom_Success() {
        // Arrange
        int roomId = 1;
        String arduinoIpAddress = "192.168.1.100";
        StateOfRoomRequest stateOfRoomRequest = new StateOfRoomRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StateOfRoomRequest> expectedRequestEntity = new HttpEntity<>(stateOfRoomRequest, headers);

        // Act
        boolean result = deviceService.updateStateOfRoom(stateOfRoomRequest, roomId);

        // Assert
        assertFalse(result);
        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    void testSendEmailToUserSuccess() {
        // Arrange
        int userId = 1;
        String message = "Test message from device";
        String userEmail = "pavlo.kokhanevych@nure.ua";

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserEntity("Maks", "sdfsd", userEmail , LocalDateTime.now(), "fff", "ff", UserType.CLIENT, new ArrayList<>())));

        // Act
        boolean result = deviceService.sendEmailToUser(message, userId);

        // Assert
        assertTrue(result);
    }


    @Test
    public void testGetInfoRoomByIP() {
        // Arrange
        String ip = "192.168.1.101";
        RoomEntity roomEntity = new RoomEntity();
        RoomDto roomDto = new RoomDto();

        // Act
        RoomDto result = deviceFunctionalService.getInfoRoomByIP(ip);

        // Assert
        assertNotNull(result);
    }


    @Test
    public void testStartVideoStream() {
        // Arrange
        int roomId = 1;
        String arduinoIpAddress = "192.168.1.1";
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setIp(arduinoIpAddress);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://" + arduinoIpAddress).build();
        WebTestClient.RequestHeadersSpec<?> spec = webTestClient.get().uri("/video-stream").accept(MediaType.APPLICATION_STREAM_JSON);
        ExchangeFunction exchangeFunction = request -> Mono.just(ClientResponse.create(HttpStatus.OK).build());
        WebClient webClient = WebClient.builder().exchangeFunction(exchangeFunction).build();
        WebClient.Builder builder = Mockito.mock(WebClient.Builder.class);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Act
        Mono<ServerResponse> response = deviceService.startVideoStream(roomId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.block().statusCode());
    }

    @Test
    public void testStopVideoStream() {
        // Arrange
        int roomId = 1;
        String arduinoIpAddress = "192.168.1.1";
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setIp(arduinoIpAddress);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://" + arduinoIpAddress).build();
        WebTestClient.RequestHeadersSpec<?> spec = webTestClient.post().uri("/stop-video-stream");
        ExchangeFunction exchangeFunction = request -> Mono.just(ClientResponse.create(HttpStatus.OK).build());
        WebClient webClient = WebClient.builder().exchangeFunction(exchangeFunction).build();
        WebClient.Builder builder = Mockito.mock(WebClient.Builder.class);
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(roomEntity));

        // Act
        Mono<ServerResponse> response = deviceService.stopVideoStream(roomId);

        // Assert
        assertNotNull(response);
    }
}
