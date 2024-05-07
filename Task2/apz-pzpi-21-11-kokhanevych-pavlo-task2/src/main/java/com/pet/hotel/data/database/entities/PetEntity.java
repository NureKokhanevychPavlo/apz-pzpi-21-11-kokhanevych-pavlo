package com.pet.hotel.data.database.entities;

import com.pet.hotel.data.enums.PetType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "pet")
@NoArgsConstructor
@Component
public class PetEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    @Setter(AccessLevel.PUBLIC)
    private int petId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_pet", nullable = false)
    private PetType typePet;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_link")
    private String photoLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pet")
    private List<RentEntity> rents;


    public PetEntity(String name, int age, float weight, PetType typePet, String description, String photoLink, UserEntity user, List<RentEntity> rents) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.typePet = typePet;
        this.description = description;
        this.photoLink = photoLink;
        this.user = user;
        this.rents = rents;
    }
}
