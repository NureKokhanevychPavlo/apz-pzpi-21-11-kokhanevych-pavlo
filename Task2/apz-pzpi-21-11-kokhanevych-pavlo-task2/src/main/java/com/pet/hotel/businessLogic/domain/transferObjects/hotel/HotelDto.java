package com.pet.hotel.businessLogic.domain.transferObjects.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    @JsonProperty("hotel_id")
    private int hotelId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("region")
    private String region;

    @JsonProperty("district")
    private String district;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number_house")
    private String numberHouse;

    public HotelDto(String name, String region, String district, String city, String street, String numberHouse) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
    }
}
