package com.pet.hotel.data.database.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
@Component
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    @Setter(AccessLevel.PUBLIC)
    private int roomId;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "area", nullable = false)
    private float area;

    @Column(name = "price_hour", nullable = false)
    private float priceHour;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "port", nullable = false)
    private String port;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private List<RentEntity> rents;

    public RoomEntity(int number, float area, float priceHour, String ip, String port, HotelEntity hotel, List<RentEntity> rents) {
        this.number = number;
        this.area = area;
        this.priceHour = priceHour;
        this.ip = ip;
        this.port = port;
        this.hotel = hotel;
        this.rents = rents;
    }
}
