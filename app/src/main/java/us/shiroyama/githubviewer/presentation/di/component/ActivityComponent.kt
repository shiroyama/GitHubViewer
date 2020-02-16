package us.shiroyama.githubviewer.presentation.di.component

import dagger.Subcomponent
import us.shiroyama.githubviewer.presentation.di.module.ActivityModule
import us.shiroyama.githubviewer.presentation.di.module.FragmentModule
import us.shiroyama.githubviewer.presentation.di.scope.ActivityScope
import us.shiroyama.githubviewer.presentation.view.activity.AccountInputActivity
import us.shiroyama.githubviewer.presentation.view.activity.RepositoryListActivity

/**
 * Activity [Subcomponent]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AccountInputActivity)
    fun inject(activity: RepositoryListActivity)
    fun plus(module: FragmentModule): FragmentComponent
}