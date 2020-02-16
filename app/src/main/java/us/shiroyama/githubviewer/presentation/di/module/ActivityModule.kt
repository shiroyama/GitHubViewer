package us.shiroyama.githubviewer.presentation.di.module

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Activity [Module]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @Provides
    fun providesActivity(): AppCompatActivity {
        return activity
    }

}