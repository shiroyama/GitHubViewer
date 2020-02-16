package us.shiroyama.githubviewer.presentation.view.fragment

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import us.shiroyama.githubviewer.R
import us.shiroyama.githubviewer.presentation.di.component.FragmentComponent
import us.shiroyama.githubviewer.presentation.presenter.AccountInputPresenter
import us.shiroyama.githubviewer.presentation.view.contract.AccountInputContract
import javax.inject.Inject

/**
 * [Fragment] for GitHub account input screen
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class AccountInputFragment : BaseFragment<AccountInputContract.View, AccountInputPresenter>(),
    AccountInputContract.View {

    @Inject
    override lateinit var presenter: AccountInputPresenter

    @BindView(R.id.account)
    lateinit var accountView: EditText

    @OnClick(R.id.view_repository_button)
    fun onClickViewRepositoryButton(button: Button) {
        presenter.onClickViewRepositoryButton(accountView.text.toString())
    }

    override val viewContract: AccountInputContract.View
        get() = this

    override val layoutRes: Int
        get() = R.layout.fragment_account_input

    override fun bindView(fragmentView: View): Unbinder {
        return ButterKnife.bind(this, fragmentView)
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun showInputError(errorRes: Int) {
        accountView.error = getString(errorRes)
        accountView.requestFocus()
    }

    companion object {
        private val TAG = AccountInputFragment::class.java.simpleName
        fun newInstance(): AccountInputFragment {
            return AccountInputFragment()
        }
    }
}