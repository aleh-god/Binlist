package by.godevelopment.binlist.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "alpha2")
    val alpha2: String? = null,
    @Json(name = "currency")
    val currency: String? = null,
    @Json(name = "emoji")
    val emoji: String? = null,
    @Json(name = "latitude")
    val latitude: Int? = null,
    @Json(name = "longitude")
    val longitude: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "numeric")
    val numeric: String? = null
)
