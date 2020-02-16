package us.shiroyama.githubviewer.presentation.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import us.shiroyama.githubviewer.BuildConfig
import us.shiroyama.githubviewer.infrastructure.rest.service.GitHubService
import javax.inject.Singleton

/**
 * A [Module] for [Retrofit]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesGitHubService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }
}