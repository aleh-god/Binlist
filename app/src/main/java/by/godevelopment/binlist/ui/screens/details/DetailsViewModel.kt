package by.godevelopment.binlist.ui.screens.details

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.interfaces.BinlistRepositoryBehaviour
import by.godevelopment.binlist.domain.models.BinDataModel
import by.godevelopment.binlist.ui.intent.IntentAction
import by.godevelopment.binlist.ui.navigation.NavigationConstVal.NAV_ARGUMENT_LABEL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val binlistRepositoryBehaviour: BinlistRepositoryBehaviour
) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    private val _uiAction = Channel<DetailsScreenUiAction>()
    val uiAction: Flow<DetailsScreenUiAction> = _uiAction.receiveAsFlow()

    private val _intentAction = Channel<IntentAction>()
    val intentAction: Flow<IntentAction> = _intentAction.receiveAsFlow()

    init {
        viewModelScope.launch {
            try {
                val cardNumber: String = checkNotNull(savedStateHandle[NAV_ARGUMENT_LABEL])
                uiState = uiState.copy(isProcessing = true)
                val data = binlistRepositoryBehaviour.getBinData(cardNumber)
                uiState = uiState.copy(binDataModel = data)
            } catch (e: Exception) {
                _uiAction.send(
                    DetailsScreenUiAction.ShowSnackbar(R.string.message_error_load_data)
                )
            } finally {
                uiState = uiState.copy(isProcessing = false)
            }
        }
    }

    fun onEvent(event: DetailsScreenUserEvent) {
        viewModelScope.launch {
            when (event) {
                DetailsScreenUserEvent.BackPressed -> {
                    _uiAction.send(DetailsScreenUiAction.NavigateBack)
                }
                DetailsScreenUserEvent.CallIntent -> {
                    uiState.binDataModel.bank?.phone?.let {
                        _intentAction.send(
                            IntentAction.Call(phoneNumber = it)
                        )
                    }
                }
                DetailsScreenUserEvent.MapIntent -> {
                    val geoLocationUri = Uri.parse("geo:${uiState.binDataModel.country?.latitude},${uiState.binDataModel.country?.longitude}")
                    _intentAction.send(
                        IntentAction.Map(geoLocation = geoLocationUri)
                    )
                }
                DetailsScreenUserEvent.WebIntent -> {
                    uiState.binDataModel.bank?.url?.let {
                        _intentAction.send(
                            IntentAction.Web(url = it)
                        )
                    }
                }
            }
        }
    }

    data class UiState(
        val binDataModel: BinDataModel = BinDataModel(),
        val isProcessing: Boolean = false,
    )
}
