package by.godevelopment.binlist.domain.models

import androidx.annotation.StringRes

sealed interface ValidatingResult<T> {

    data class Saccess<T>(val checkedValue: T) : ValidatingResult<T>
    data class Error<T>(@StringRes val message: Int) : ValidatingResult<T>
}
