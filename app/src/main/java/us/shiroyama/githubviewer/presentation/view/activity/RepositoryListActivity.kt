package us.shiroyama.githubviewer.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.presentation.view.fragment.RepositoryListFragment

/**
 * [DaggerActivity] for GitHub repository list
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class RepositoryListActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        component.inject(this)

        val account = intent.getStringExtra(BundleKey.ACCOUNT.name)
        replaceFragment(RepositoryListFragment.newInstance(account), R.id.container)
    }

    companion object {
        fun newIntent(context: Context, account: String): Intent {
            return Intent(context, RepositoryListActivity::class.java).apply {
                putExtra(BundleKey.ACCOUNT.name, account)
            }
        }
    }

    enum class BundleKey {
        ACCOUNT
    }
}