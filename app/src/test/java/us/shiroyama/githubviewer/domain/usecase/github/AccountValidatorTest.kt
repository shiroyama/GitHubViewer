package us.shiroyama.githubviewer.domain.usecase.github

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import us.shiroyama.githubviewer.R

@RunWith(RobolectricTestRunner::class)
class AccountValidatorTest {
    private val accountValidator = AccountValidator()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun validate_returnsSuccessfully_whenAlphaNumeric() {
        val result = accountValidator.validate("abc123")
        assertThat(result.isValid).isTrue()
        assertThat(result.errorRes).isEqualTo(0)
    }

    @Test
    fun validate_returnsSuccessfully_whenOnlyAlphabets() {
        val result = accountValidator.validate("abc")
        assertThat(result.isValid).isTrue()
        assertThat(result.errorRes).isEqualTo(0)
    }

    @Test
    fun validate_returnsSuccessfully_whenOnlyNumbers() {
        val result = accountValidator.validate("123")
        assertThat(result.isValid).isTrue()
        assertThat(result.errorRes).isEqualTo(0)
    }

    @Test
    fun validate_returnsUnsuccessfully_whenContainingSpecialCharacters() {
        val result = accountValidator.validate("abc@123")
        assertThat(result.isValid).isFalse()
        assertThat(result.errorRes).isEqualTo(R.string.error_invalid_account)
    }

    @Test
    fun validate_returnsUnsuccessfully_whenBlank() {
        val result = accountValidator.validate("")
        assertThat(result.isValid).isFalse()
        assertThat(result.errorRes).isEqualTo(R.string.error_field_required)
    }

    @Test
    fun validate_returnsUnsuccessfully_whenNull() {
        val result = accountValidator.validate(null)
        assertThat(result.isValid).isFalse()
        assertThat(result.errorRes).isEqualTo(R.string.error_field_required)
    }
}