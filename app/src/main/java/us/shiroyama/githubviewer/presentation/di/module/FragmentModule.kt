package us.shiroyama.githubviewer.presentation.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

/**
 * Fragment [Module]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    fun providesFragmentManager(): FragmentManager {
        return fragment.requireFragmentManager()
    }

    @Provides
    fun providesContext(): Context {
        return fragment.requireContext()
    }

}