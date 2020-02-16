package us.shiroyama.githubviewer.presentation.presenter

import us.shiroyama.githubviewer.domain.usecase.github.AccountValidator
import us.shiroyama.githubviewer.presentation.view.activity.RepositoryListActivity
import us.shiroyama.githubviewer.presentation.view.contract.AccountInputContract
import javax.inject.Inject

/**
 * Presenter for GitHub account input
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class AccountInputPresenter @Inject constructor(
    private val validator: AccountValidator
) : BasePresenter<AccountInputContract.View>(), AccountInputContract.Interaction {

    override fun onClickViewRepositoryButton(input: String?) {
        val result = validator.validate(input)
        if (!result.isValid) {
            view.showInputError(result.errorRes)
            return
        }

        input?.let {
            startActivity(RepositoryListActivity.newIntent(getContext(), it))
        }
    }

    companion object {
        private val TAG = AccountInputPresenter::class.java.simpleName
    }

}