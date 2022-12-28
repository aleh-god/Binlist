package by.godevelopment.binlist.ui.screens.details


sealed interface DetailsScreenUserEvent {

    object BackPressed : DetailsScreenUserEvent
    object CallIntent : DetailsScreenUserEvent
    object MapIntent : DetailsScreenUserEvent
    object WebIntent : DetailsScreenUserEvent
}