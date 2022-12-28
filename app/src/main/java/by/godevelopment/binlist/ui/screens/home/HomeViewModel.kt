package by.godevelopment.binlist.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.interfaces.HistoryRepositoryBehaviour
import by.godevelopment.binlist.domain.models.ValidatingResult
import by.godevelopment.binlist.domain.usecases.NumberValidateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val historyRepositoryBehaviour: HistoryRepositoryBehaviour,
    private val numberValidateUseCase: NumberValidateUseCase
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    private val _uiAction = Channel<HomeScreenUiAction>()
    val uiAction: Flow<HomeScreenUiAction> = _uiAction.receiveAsFlow()

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isProcessing = true)
            historyRepositoryBehaviour
                .getQueryList()
                .onStart {
                    uiState = uiState.copy(isProcessing = true)
                }
                .catch { error ->
                    uiState = uiState.copy(isProcessing = false)
                    _uiAction.send(
                        HomeScreenUiAction.ShowSnackbar(message = R.string.message_error_load_data)
                    )
                }
                .onEach {
                    uiState = uiState.copy(
                        isProcessing = false,
                        queries = it
                    )
                }
                .collect()
        }
    }

    fun onEvent(event: HomeScreenUserEvent) {
        when (event) {
            is HomeScreenUserEvent.HistoryItemOnClick -> {
                navigateWith(event.number)
            }
            is HomeScreenUserEvent.NumberChanged -> {
                uiState = uiState.copy(
                    number = event.number,
                    hasError = false
                )
            }
            HomeScreenUserEvent.QueryBinDataOnClick -> {
                val result = numberValidateUseCase.invoke(uiState.number)
                when (result) {
                    is ValidatingResult.Error -> {
                        viewModelScope.launch {
                            uiState = uiState.copy(
                                hasError = true
                            )
                            _uiAction.send(
                                HomeScreenUiAction.ShowSnackbar(message = result.message)
                            )
                        }
                    }
                    is ValidatingResult.Saccess -> {
                        uiState = uiState.copy(hasError = false)
                        saveQuery(result.checkedValue)
                        navigateWith(result.checkedValue)
                    }
                }
            }
        }
    }

    private fun saveQuery(checkedValue: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isProcessing = true)
            val newQueries = uiState.queries.toMutableList().apply {
                add(checkedValue)
            }
            historyRepositoryBehaviour.saveQueryList(newQueries)
            uiState = uiState.copy(isProcessing = false)
        }
    }

    private fun navigateWith(number: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isProcessing = true)
            _uiAction.send(
                HomeScreenUiAction.NavigateToDetailWith(number)
            )
            uiState = uiState.copy(isProcessing = false)
        }
    }

    data class UiState(
        val number: String = "",
        val queries: List<String> = emptyList(),
        val isProcessing: Boolean = false,
        val hasError: Boolean = false
    )
}
