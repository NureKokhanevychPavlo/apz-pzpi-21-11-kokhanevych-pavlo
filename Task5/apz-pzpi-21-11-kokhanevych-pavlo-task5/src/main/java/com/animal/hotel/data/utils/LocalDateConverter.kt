package com.animal.hotel.data.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.time.LocalDate
import java.lang.reflect.Type
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor


class LocalDateConverter : JsonSerializer<LocalDate?>,
    JsonDeserializer<LocalDate?> {
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE.format(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        return DateTimeFormatter.ISO_LOCAL_DATE.parse(
            json.asString
        ) { temporal: TemporalAccessor? ->
            LocalDate.from(
                temporal
            )
        }
    }
}