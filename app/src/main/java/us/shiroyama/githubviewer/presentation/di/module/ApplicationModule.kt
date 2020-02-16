package us.shiroyama.githubviewer.presentation.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Application [Module]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module(includes = [GsonModule::class, OkHttpModule::class, RetrofitModule::class, GitHubModule::class, PicassoModule::class])
class ApplicationModule(private val application: Application) {
    @Provides
    fun providesApplication(): Application {
        return application
    }

}