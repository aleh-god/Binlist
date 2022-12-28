package by.godevelopment.binlist.domain

import by.godevelopment.binlist.R
import by.godevelopment.binlist.domain.models.ValidatingResult
import by.godevelopment.binlist.domain.usecases.NumberValidateUseCase
import org.junit.Assert
import org.junit.Test

internal class NumberValidateUseCaseTest {

    private val useCase = NumberValidateUseCase()

    @Test
    fun invoke_passNull_returnError() {

        val actual = useCase.invoke(null)
        val expected =  ValidatingResult.Error<Int>(message = R.string.message_error_not_number)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_passEmptyString_returnError() {

        val actual = useCase.invoke("")
        val expected = ValidatingResult.Error<Int>(message = R.string.message_error_not_number)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_passWrongString_returnError() {

        val actual = useCase.invoke("123asx")
        val expected = ValidatingResult.Error<Int>(message = R.string.message_error_not_number)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_passCorrectString_returnValue() {
        val actual = useCase.invoke("12345678")
        val expected = ValidatingResult.Saccess<String>("12345678")

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_passCorrectStringWithZero_returnValue() {

        val actual = useCase.invoke("00123456")
        val expected = ValidatingResult.Saccess<String>("00123456")

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_pass_greater_12_String_returnValue() {

        val actual = useCase.invoke("12345678912345")
        val expected = ValidatingResult.Error<String>(
            message = R.string.message_error_number_greater_12
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun invoke_pass_less_4_String_returnValue() {

        val actual = useCase.invoke("123")
        val expected = ValidatingResult.Error<String>(
            message = R.string.message_error_number_less_4
        )

        Assert.assertEquals(expected, actual)
    }
}