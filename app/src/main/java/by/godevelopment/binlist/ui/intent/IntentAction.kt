package by.godevelopment.binlist.ui.intent

import android.net.Uri

sealed interface IntentAction {

    data class Call(val phoneNumber: String) : IntentAction
    data class Map(val geoLocation: Uri) : IntentAction
    data class Web(val url: String) : IntentAction
}
