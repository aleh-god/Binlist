package by.godevelopment.binlist.data

import by.godevelopment.binlist.di.IoDispatcher
import by.godevelopment.binlist.domain.interfaces.BinlistRepositoryBehaviour
import by.godevelopment.binlist.domain.interfaces.HistoryRepositoryBehaviour
import by.godevelopment.binlist.domain.models.BinDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val binlistApi: BinlistApi,
    private val historyStoreBehaviour: HistoryStoreBehaviour
) : BinlistRepositoryBehaviour,
    HistoryRepositoryBehaviour
{
    override fun getQueryList(): Flow<List<String>> = historyStoreBehaviour.getQueryList()

    override suspend fun saveQueryList(newQueryList: List<String>) {
        historyStoreBehaviour.saveQueryList(newQueryList)
    }

    override suspend fun getBinData(number: String): BinDataModel =
        withContext(ioDispatcher) {
            binlistApi.getBinData(number)
        }
}
