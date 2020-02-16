package us.shiroyama.githubviewer.presentation.di.component

import dagger.Subcomponent
import us.shiroyama.githubviewer.presentation.di.module.FragmentModule
import us.shiroyama.githubviewer.presentation.di.scope.FragmentScope
import us.shiroyama.githubviewer.presentation.view.fragment.AccountInputFragment
import us.shiroyama.githubviewer.presentation.view.fragment.RepositoryListFragment

/**
 * Fragment [Subcomponent]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: AccountInputFragment)
    fun inject(fragment: RepositoryListFragment)
}