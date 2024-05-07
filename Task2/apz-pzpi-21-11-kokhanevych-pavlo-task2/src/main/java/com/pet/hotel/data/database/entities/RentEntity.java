package com.pet.hotel.data.database.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "rent")
@NoArgsConstructor
@Component
public class RentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    @Setter(AccessLevel.PUBLIC)
    private int rentId;

    @Column(name = "begin_date", nullable = false)
    private LocalDateTime beginDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rent")
    private List<DietEntity> diets;

    public RentEntity(LocalDateTime beginDate, LocalDateTime endDate, RoomEntity room, PetEntity pet, List<DietEntity> diets) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.room = room;
        this.pet = pet;
        this.diets = diets;
    }
}
