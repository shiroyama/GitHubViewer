package us.shiroyama.githubviewer.presentation.di.component

import dagger.Component
import us.shiroyama.githubviewer.presentation.di.module.ActivityModule
import us.shiroyama.githubviewer.presentation.di.module.ApplicationModule
import us.shiroyama.githubviewer.presentation.view.MyApplication
import javax.inject.Singleton

/**
 * Application [Component]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: MyApplication)
    fun plus(module: ActivityModule): ActivityComponent
}