package by.godevelopment.binlist.ui.navigation

import androidx.annotation.StringRes
import by.godevelopment.binlist.R

enum class Route (
    val label: String,
    @StringRes
    val screenName: Int
) {
    HOME("home", R.string.screen_name_home),
    DETAILS("details", R.string.screen_name_details),
}