package us.shiroyama.githubviewer.presentation.view

import android.app.Application
import us.shiroyama.githubviewer.presentation.di.component.ApplicationComponent
import us.shiroyama.githubviewer.presentation.di.component.DaggerApplicationComponent
import us.shiroyama.githubviewer.presentation.di.module.ApplicationModule

/**
 * Custom [Application]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class MyApplication : Application() {
    var applicationComponent: ApplicationComponent? = null
    val component: ApplicationComponent
        get() = applicationComponent!!

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}