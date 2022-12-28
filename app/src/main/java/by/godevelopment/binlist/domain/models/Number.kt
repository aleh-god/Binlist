package by.godevelopment.binlist.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    @Json(name = "length")
    val length: Int? = null,
    @Json(name = "luhn")
    val luhn: Boolean? = null
)
