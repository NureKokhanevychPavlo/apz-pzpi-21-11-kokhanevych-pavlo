package com.pet.hotel.businessLogic.domain.interfaces;

import com.pet.hotel.businessLogic.domain.transferObjects.rent.RentDto;
import com.pet.hotel.businessLogic.domain.transferObjects.rent.ScheduleFeedResponse;

import java.util.List;

public interface ScheduleService {

    List<ScheduleFeedResponse> getScheduleFeed(int rentId);

    boolean updateTimeOfScheduleFeed(List<ScheduleFeedResponse> scheduleFeedResponses);

    List<ScheduleFeedResponse> autogenerateSchedule(RentDto rentDto, int numberOfBlocks);
}
