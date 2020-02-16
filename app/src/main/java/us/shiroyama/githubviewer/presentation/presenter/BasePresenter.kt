package us.shiroyama.githubviewer.presentation.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import us.shiroyama.githubviewer.presentation.view.contract.BaseView

/**
 * Base Presenter
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class BasePresenter<V : BaseView> {
    lateinit var view: V

    open fun onAttach(context: Context) {
        // NOP
    }

    open fun onViewCreated(savedInstanceState: Bundle?) {
        // NOP
    }

    open fun onResume() {
        // NOP
    }

    open fun onPause() {
        // NOP
    }

    open fun onDestroyView() {
        // NOP
    }

    open fun onDetach() {
        // NOP
    }

    fun getContext(): Context {
        return view.getFragmentContext()
    }

    fun getActivity(): FragmentActivity {
        return view.getFragmentActivity()
    }

    fun getArguments(): Bundle {
        return view.getFragmentArguments()
    }

    fun startActivity(intent: Intent) {
        view.startActivity(intent)
    }

    fun finishActivity() {
        view.finishActivity()
    }
}