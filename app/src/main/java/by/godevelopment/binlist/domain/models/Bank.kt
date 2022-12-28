package by.godevelopment.binlist.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "phone")
    val phone: String? = null,
    @Json(name = "url")
    val url: String? = null
)
