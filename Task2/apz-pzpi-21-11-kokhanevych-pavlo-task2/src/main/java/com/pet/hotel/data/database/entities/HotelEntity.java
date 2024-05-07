package com.pet.hotel.data.database.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "hotel")
@NoArgsConstructor
@Component
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    @Setter(AccessLevel.PUBLIC)
    private int hotelId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number_house", nullable = false)
    private String numberHouse;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

    public HotelEntity(String name, String region, String district, String city, String street, String numberHouse, List<RoomEntity> rooms) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
        this.rooms = rooms;
    }
}
