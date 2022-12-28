package by.godevelopment.binlist.data

import by.godevelopment.binlist.domain.models.BinDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {

    @GET("{number}")
    suspend fun getBinData(@Path("number") number: String): BinDataModel
}
