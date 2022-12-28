package by.godevelopment.binlist.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BinDataModel(
    @Json(name = "bank")
    val bank: Bank? = null,
    @Json(name = "brand")
    val brand: String? = null,
    @Json(name = "country")
    val country: Country? = null,
    @Json(name = "number")
    val number: Number? = null,
    @Json(name = "prepaid")
    val prepaid: Boolean? = null,
    @Json(name = "scheme")
    val scheme: String? = null,
    @Json(name = "type")
    val type: String? = null
)
