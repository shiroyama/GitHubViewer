package us.shiroyama.githubviewer.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import butterknife.Unbinder
import us.shiroyama.githubviewer.presentation.di.component.FragmentComponent
import us.shiroyama.githubviewer.presentation.presenter.BasePresenter
import us.shiroyama.githubviewer.presentation.view.contract.BaseView

/**
 * Base [Fragment]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : DaggerFragment() {
    private lateinit var _presenter: P
    private lateinit var unbinder: Unbinder

    protected abstract val viewContract: V
    protected abstract val presenter: P

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun bindView(fragmentView: View): Unbinder
    protected abstract fun inject(component: FragmentComponent)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(component)
        _presenter = presenter
        _presenter.onAttach(context)
        _presenter.view = viewContract
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = bindView(view)
        _presenter.onViewCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        _presenter.onResume()
    }

    override fun onPause() {
        _presenter.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        _presenter.onDestroyView()
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onDetach() {
        _presenter.onDetach()
        super.onDetach()
    }

    fun getFragmentContext(): Context {
        return requireContext()
    }

    fun getFragmentActivity(): FragmentActivity {
        return requireActivity()
    }

    fun getFragmentArguments(): Bundle {
        return requireArguments()
    }

    fun finishActivity() {
        getFragmentActivity().finish()
    }
}