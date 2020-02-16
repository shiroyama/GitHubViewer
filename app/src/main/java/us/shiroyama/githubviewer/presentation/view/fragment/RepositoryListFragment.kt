package us.shiroyama.githubviewer.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.presentation.di.component.FragmentComponent
import us.shiroyama.githubviewer.presentation.presenter.RepositoryListPresenter
import us.shiroyama.githubviewer.presentation.view.activity.RepositoryListActivity
import us.shiroyama.githubviewer.presentation.view.adapter.RepositoryListAdapter
import us.shiroyama.githubviewer.presentation.view.contract.RepositoryListContract
import us.shiroyama.githubviewer.presentation.viewmodel.RepositoryViewModel
import javax.inject.Inject

/**
 * [Fragment] for GitHub repository list
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class RepositoryListFragment : BaseFragment<RepositoryListContract.View, RepositoryListPresenter>(),
    RepositoryListContract.View {

    @Inject
    override lateinit var presenter: RepositoryListPresenter

    @Inject
    lateinit var adapter: RepositoryListAdapter

    @BindView(R.id.list)
    lateinit var recyclerView: RecyclerView

    override val viewContract: RepositoryListContract.View
        get() = this

    override val layoutRes: Int
        get() = R.layout.fragment_list

    override fun bindView(fragmentView: View): Unbinder {
        return ButterKnife.bind(this, fragmentView)
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            recyclerView.layoutManager = LinearLayoutManager(it)
            recyclerView.adapter = adapter
        }
    }

    override fun showList(repos: List<RepositoryViewModel>) {
        Log.d(TAG, "showList")
        adapter.setViewModels(repos)
    }

    companion object {
        private val TAG = RepositoryListFragment::class.java.simpleName
        fun newInstance(account: String): RepositoryListFragment {
            val args = Bundle().apply {
                putString(RepositoryListActivity.BundleKey.ACCOUNT.name, account)
            }
            return RepositoryListFragment().apply {
                arguments = args
            }
        }
    }
}