package com.example.hw2_m6.data.model

import com.example.hw2_m6.ui.utils.Status
import java.io.Serializable

data class BaseResponse<T>(
    var results: List<T>
) : Serializable

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var image: String,
    var species: String,
    var origin: Origin,
    var location: Location,
) : Serializable

data class Origin(
    var name: String
) : Serializable

data class Location(
    var name: String
) : Serializable


