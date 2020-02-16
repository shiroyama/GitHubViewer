package us.shiroyama.githubviewer.presentation.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * [Gson] [Module]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class GsonModule {
    @Singleton
    @Provides
    fun providesGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @Singleton
    @Provides
    fun providesGson(gsonBuilder: GsonBuilder): Gson {
        return gsonBuilder.create()
    }
}