package com.example.hw2_m6.ui.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CharacterStatusDeserializer : JsonDeserializer<Status> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Status {
        val statusString = json?.asString
        for (enum in Status.values()) {
            if (enum.provider == statusString) {
                return enum
            }
        }
        throw IllegalArgumentException("Unknown tsp $statusString!")
    }
}
