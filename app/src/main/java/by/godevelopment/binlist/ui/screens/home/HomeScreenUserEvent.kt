package by.godevelopment.binlist.ui.screens.home

sealed interface HomeScreenUserEvent {

    object QueryBinDataOnClick : HomeScreenUserEvent
    data class HistoryItemOnClick(val number: String) : HomeScreenUserEvent
    data class NumberChanged(val number: String) : HomeScreenUserEvent
}
