package us.shiroyama.githubviewer.presentation.view.contract

import androidx.annotation.StringRes
import us.shiroyama.githubviewer.presentation.presenter.RepositoryListPresenter
import us.shiroyama.githubviewer.presentation.view.fragment.RepositoryListFragment


/**
 * A contract between [RepositoryListFragment] and [RepositoryListPresenter]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface AccountInputContract {
    interface View : BaseView {
        fun showInputError(@StringRes errorRes: Int)
    }

    interface Interaction {
        fun onClickViewRepositoryButton(input: String?)
    }
}