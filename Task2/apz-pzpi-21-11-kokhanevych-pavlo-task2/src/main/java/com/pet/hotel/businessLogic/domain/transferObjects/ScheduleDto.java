package com.pet.hotel.businessLogic.domain.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    @JsonProperty("schedule_id")
    private int scheduleId;

    @JsonProperty("date_time")
    private LocalDateTime dateTime;

    @JsonProperty("diet")
    private DietDto dietDto;

    public ScheduleDto(LocalDateTime dateTime, DietDto dietDto) {
        this.dateTime = dateTime;
        this.dietDto = dietDto;
    }
}
