package by.godevelopment.binlist.domain.interfaces

import kotlinx.coroutines.flow.Flow

interface HistoryRepositoryBehaviour {

    fun getQueryList(): Flow<List<String>>

    suspend fun saveQueryList(newQueryList: List<String>)
}
