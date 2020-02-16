package us.shiroyama.githubviewer.presentation.view.contract

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Base view contract between presenter
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface BaseView {
    fun startActivity(intent: Intent)

    fun finishActivity()

    fun getFragmentContext(): Context

    fun getFragmentActivity(): FragmentActivity

    fun getFragmentArguments(): Bundle
}