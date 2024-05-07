package com.pet.hotel.data.database.entities;

import com.pet.hotel.data.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@Component
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Setter(AccessLevel.PUBLIC)
    private int userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "photo_link")
    private String photoLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user", nullable = false)
    private UserType typeUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<PetEntity> pets;

    public UserEntity(String fullName, String passwordHash, String email, LocalDateTime birthDate, String phoneNumber, String photoLink, UserType typeUser, List<PetEntity> listOfPets) {
        this.fullName = fullName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.photoLink = photoLink;
        this.typeUser = typeUser;
        this.pets = listOfPets;
    }
}
