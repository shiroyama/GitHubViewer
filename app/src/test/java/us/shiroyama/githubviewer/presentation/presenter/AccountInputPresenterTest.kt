package us.shiroyama.githubviewer.presentation.presenter

import com.nhaarman.mockitokotlin2.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.domain.usecase.github.AccountValidator
import us.shiroyama.githubviewer.presentation.view.contract.AccountInputContract

@RunWith(RobolectricTestRunner::class)
class AccountInputPresenterTest {
    private val validator: AccountValidator = spy(AccountValidator())
    private lateinit var view: AccountInputContract.View
    private lateinit var accountInputPresenter: AccountInputPresenter

    @Before
    fun setUp() {
        view = mock()
        whenever(view.getFragmentContext()).thenReturn(mock())
        accountInputPresenter = AccountInputPresenter(validator)
        accountInputPresenter.view = view
    }

    @After
    fun tearDown() {
    }

    @Test
    fun onClickViewRepositoryButton_showError_whenInvalidInput() {
        accountInputPresenter.onClickViewRepositoryButton("abc@123")
        verify(view, times(1)).showInputError(eq(R.string.error_invalid_account))
    }

    @Test
    fun onClickViewRepositoryButton_showError_whenBlank() {
        accountInputPresenter.onClickViewRepositoryButton("")
        verify(view, times(1)).showInputError(eq(R.string.error_field_required))
    }

    @Test
    fun onClickViewRepositoryButton_showError_whenNull() {
        accountInputPresenter.onClickViewRepositoryButton(null)
        verify(view, times(1)).showInputError(eq(R.string.error_field_required))
    }

    @Test
    fun onClickViewRepositoryButton_goesNextScreen_whenValidInput() {
        accountInputPresenter.onClickViewRepositoryButton("abc123")
        verify(view, times(0)).showInputError(any())
        verify(view, times(1)).startActivity(any())
    }
}