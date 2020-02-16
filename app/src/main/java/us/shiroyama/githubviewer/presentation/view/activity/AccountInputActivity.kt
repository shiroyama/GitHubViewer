package us.shiroyama.githubviewer.presentation.view.activity

import android.os.Bundle
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.presentation.view.fragment.AccountInputFragment

/**
 * [DaggerActivity] for GitHub account input screen
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class AccountInputActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_input)
        component.inject(this)
        replaceFragment(AccountInputFragment.newInstance(), R.id.container)
    }
}