package com.pet.hotel.data.database.entities;

import com.pet.hotel.data.enums.FoodType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "diet")
@NoArgsConstructor
@Component
public class DietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    @Setter(AccessLevel.PUBLIC)
    private int dietId;

    @Column(name = "portion", nullable = false)
    private float portion;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_food", nullable = false)
    private FoodType typeFood;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rent_id", nullable = false)
    private RentEntity rent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "diet")
    private List<ScheduleEntity> schedules;

    public DietEntity(float portion, FoodType typeFood, RentEntity rent, List<ScheduleEntity> schedules) {
        this.portion = portion;
        this.typeFood = typeFood;
        this.rent = rent;
        this.schedules = schedules;
    }
}
