package by.godevelopment.binlist.ui.screens.details

import androidx.annotation.StringRes

sealed interface DetailsScreenUiAction {

    object NavigateBack : DetailsScreenUiAction
    data class ShowSnackbar(@StringRes val message: Int): DetailsScreenUiAction
}
