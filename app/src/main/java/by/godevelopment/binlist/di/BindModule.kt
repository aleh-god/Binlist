package by.godevelopment.binlist.di

import by.godevelopment.binlist.data.HistoryStoreBehaviour
import by.godevelopment.binlist.data.RepositoryImplementation
import by.godevelopment.binlist.domain.interfaces.BinlistRepositoryBehaviour
import by.godevelopment.binlist.domain.interfaces.HistoryRepositoryBehaviour
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("FunctionName")
@InstallIn(SingletonComponent::class)
@Module
interface BindModule {

    @Binds
    fun provide_SharedPrefImpl_To_HistoryStoreBehaviour(
        dataStoreImpl: HistoryStoreBehaviour.DataStoreImpl
    ): HistoryStoreBehaviour

    @Binds
    fun provide_RepositoryImplementation_To_BinlistRepositoryBehaviour(
        repositoryImplementation: RepositoryImplementation
    ): BinlistRepositoryBehaviour

    @Binds
    fun provide_RepositoryImplementation_To_HistoryRepositoryBehaviour(
        repositoryImplementation: RepositoryImplementation
    ): HistoryRepositoryBehaviour
}
