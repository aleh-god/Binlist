package by.godevelopment.binlist.ui.screens.home

import androidx.annotation.StringRes

sealed interface HomeScreenUiAction {

    data class NavigateToDetailWith(val value: String): HomeScreenUiAction
    data class ShowSnackbar(@StringRes val message: Int): HomeScreenUiAction
}
