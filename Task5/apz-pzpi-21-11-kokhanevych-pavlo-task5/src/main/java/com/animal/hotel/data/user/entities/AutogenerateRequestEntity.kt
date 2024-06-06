package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.AutogenerateRequest
import com.google.gson.annotations.SerializedName

data class AutogenerateRequestEntity(
    @SerializedName("rent") val rentEntity: RentEntity,
    @SerializedName("number_of_blocks") val numberOfBlocks: Int
) {
    fun toAutogenerateRequest(): AutogenerateRequest {
        return  AutogenerateRequest(rentEntity.toRent(), numberOfBlocks)
    }

    companion object {
        fun toAutogenerateRequestEntity(request: AutogenerateRequest) =  AutogenerateRequestEntity (
            RentEntity.toRentEntity(request.rent),
            request.numberOfBlocks
        )
    }
}
