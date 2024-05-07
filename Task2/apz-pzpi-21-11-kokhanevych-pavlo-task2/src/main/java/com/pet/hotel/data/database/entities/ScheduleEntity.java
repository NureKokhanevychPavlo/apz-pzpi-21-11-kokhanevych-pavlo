package com.pet.hotel.data.database.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "schedule")
@NoArgsConstructor
@Component
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    @Setter(AccessLevel.PUBLIC)
    private int scheduleId;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "diet_id", nullable = false)
    private DietEntity diet;

    public ScheduleEntity(LocalDateTime dateTime, DietEntity diet) {
        this.dateTime = dateTime;
        this.diet = diet;
    }
}
