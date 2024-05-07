package com.pet.hotel.businessLogic.domain.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.hotel.businessLogic.domain.transferObjects.hotel.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    @JsonProperty("room_id")
    private int roomId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("area")
    private float area;

    @JsonProperty("price_hour")
    private float priceHour;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("port")
    private String port;

    @JsonProperty("hotel")
    private HotelDto hotelDto;

    public RoomDto(int number, float area, float priceHour, String ip, String port, HotelDto hotelDto) {
        this.number = number;
        this.area = area;
        this.priceHour = priceHour;
        this.ip = ip;
        this.port = port;
        this.hotelDto = hotelDto;
    }
}
