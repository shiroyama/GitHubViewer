package us.shiroyama.githubviewer.presentation.view.fragment

import androidx.fragment.app.Fragment
import us.shiroyama.githubviewer.presentation.di.component.FragmentComponent
import us.shiroyama.githubviewer.presentation.di.module.FragmentModule
import us.shiroyama.githubviewer.presentation.view.activity.DaggerActivity

/**
 * Dagger capable [Fragment]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
open class DaggerFragment : Fragment() {
    val component: FragmentComponent by lazy {
        val activity = activity as DaggerActivity
        activity.component.plus(FragmentModule(this))
    }
}