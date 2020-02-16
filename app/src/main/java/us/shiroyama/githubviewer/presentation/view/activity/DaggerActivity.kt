package us.shiroyama.githubviewer.presentation.view.activity

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import us.shiroyama.githubviewer.presentation.di.component.ActivityComponent
import us.shiroyama.githubviewer.presentation.di.module.ActivityModule
import us.shiroyama.githubviewer.presentation.view.MyApplication

/**
 * Dagger capable [AppCompatActivity]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class DaggerActivity : AppCompatActivity() {
    val component: ActivityComponent by lazy {
        val application = application as MyApplication
        application.component.plus(ActivityModule(this))
    }

    fun replaceFragment(fragment: Fragment, @IdRes containerId: Int) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        ft.commit()
    }
}