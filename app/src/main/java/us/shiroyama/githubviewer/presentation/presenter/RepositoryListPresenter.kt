package us.shiroyama.githubviewer.presentation.presenter

import android.os.Bundle
import android.util.Log
import us.shiroyama.githubviewer.domain.model.RepositoryModel
import us.shiroyama.githubviewer.domain.usecase.github.GetRepositories
import us.shiroyama.githubviewer.presentation.mapper.RepoViewModelMapper
import us.shiroyama.githubviewer.presentation.view.activity.RepositoryListActivity
import us.shiroyama.githubviewer.presentation.view.contract.RepositoryListContract
import javax.inject.Inject

/**
 * Presenter for GitHub repository list
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class RepositoryListPresenter @Inject constructor(
    private val getRepositories: GetRepositories,
    private val mapper: RepoViewModelMapper
) : BasePresenter<RepositoryListContract.View>(), RepositoryListContract.Interaction {
    override fun onViewCreated(savedInstanceState: Bundle?) {
        getArguments().getString(RepositoryListActivity.BundleKey.ACCOUNT.name)?.let {
            val params = GetRepositories.Params(it)
            getRepositories.execute(
                { repoModels: List<RepositoryModel> ->
                    Log.d(
                        TAG,
                        "repoModels: " + repoModels.size
                    )
                    view.showList(mapper.convert(repoModels))
                },
                { throwable: Throwable ->
                    Log.e(
                        TAG,
                        throwable.message,
                        throwable
                    )
                },
                params
            )
        }
    }

    override fun onDestroyView() {
        getRepositories.clearDisposable()
    }

    override fun onItemClick() {
        Log.d(RepositoryListPresenter::class.java.simpleName, "onItemClick")
    }

    companion object {
        private val TAG = RepositoryListPresenter::class.java.simpleName
    }

}