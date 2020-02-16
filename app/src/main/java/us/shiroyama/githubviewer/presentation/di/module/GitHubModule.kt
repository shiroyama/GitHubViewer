package us.shiroyama.githubviewer.presentation.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import us.shiroyama.githubviewer.domain.repository.GitHubRepository
import us.shiroyama.githubviewer.infrastructure.entity.mapper.RepositoryMapper
import us.shiroyama.githubviewer.infrastructure.repository.GitHubInfraRepository
import us.shiroyama.githubviewer.infrastructure.repository.datasource.remote.RemoteGitHubDataSource
import us.shiroyama.githubviewer.infrastructure.repository.datasource.remote.RestGitHubDataSource
import us.shiroyama.githubviewer.infrastructure.rest.service.GitHubService
import javax.inject.Singleton

/**
 * A GitHub [Module]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Module
class GitHubModule {
    @Singleton
    @Provides
    fun providesRemoteGitHubDataSource(gitHubService: GitHubService): RemoteGitHubDataSource {
        return RestGitHubDataSource(gitHubService)
    }

    @Singleton
    @Provides
    fun providesGitHubRepository(
        remoteGitHubDataSource: RemoteGitHubDataSource,
        repositoryMapper: RepositoryMapper
    ): GitHubRepository {
        return GitHubInfraRepository(remoteGitHubDataSource, repositoryMapper)
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun providesRepoMapper(): RepositoryMapper {
        return RepositoryMapper()
    }
}