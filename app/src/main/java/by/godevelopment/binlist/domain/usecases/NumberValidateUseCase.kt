package by.godevelopment.binlist.domain.usecases

import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.models.ValidatingResult
import javax.inject.Inject

class NumberValidateUseCase @Inject constructor() {

    private val lowerValueLimit = 4
    private val upperValueLimit = 12

    operator fun invoke(input: String?): ValidatingResult<String> {

        if (input.isNullOrBlank() || !isNumeric(input)) return ValidatingResult.Error<String>(
            message = R.string.message_error_not_number
        )
        if (input.length < lowerValueLimit) return ValidatingResult.Error<String>(
            message = R.string.message_error_number_less_4
        )
        if (input.length > upperValueLimit) return ValidatingResult.Error<String>(
            message = R.string.message_error_number_greater_12
        )
        return ValidatingResult.Saccess<String>(input)
    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { char ->
            val result = char.isDigit()
            result
        }
    }
}
