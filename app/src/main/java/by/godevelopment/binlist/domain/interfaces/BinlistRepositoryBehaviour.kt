package by.godevelopment.binlist.domain.interfaces

import by.godevelopment.binlist.domain.models.BinDataModel

interface BinlistRepositoryBehaviour {

    suspend fun getBinData(number: String): BinDataModel
}
