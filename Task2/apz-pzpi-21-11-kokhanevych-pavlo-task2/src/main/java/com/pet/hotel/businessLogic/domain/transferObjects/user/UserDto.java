package com.pet.hotel.businessLogic.domain.transferObjects.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.hotel.data.enums.UserType;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class UserDto {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("password_hash")
    private String passwordHash;

    @JsonProperty("email")
    private String email;

    @JsonProperty("birth_date")
    private LocalDateTime birthDate;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("photo_link")
    private String photoLink;

    @JsonProperty("type_user")
    private UserType typeUser;

    public UserDto(String fullName, String passwordHash, String email, LocalDateTime birthDate, String phoneNumber, String photoLink, UserType typeUser) {
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.photoLink = photoLink;
        this.typeUser = typeUser;
    }
}
