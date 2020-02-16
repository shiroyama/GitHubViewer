package us.shiroyama.githubviewer.presentation.view.contract

import us.shiroyama.githubviewer.presentation.presenter.RepositoryListPresenter
import us.shiroyama.githubviewer.presentation.view.fragment.RepositoryListFragment
import us.shiroyama.githubviewer.presentation.viewmodel.RepositoryViewModel


/**
 * A contract between [RepositoryListFragment] and [RepositoryListPresenter]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface RepositoryListContract {
    interface View : BaseView {
        fun showList(repos: List<RepositoryViewModel>)
    }

    interface Interaction {
        fun onItemClick()
    }
}