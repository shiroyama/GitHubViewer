package us.shiroyama.githubviewer.infrastructure.repository.datasource.remote

import io.reactivex.Single
import us.shiroyama.githubviewer.infrastructure.entity.RepositoryEntity
import us.shiroyama.githubviewer.infrastructure.rest.service.GitHubService
import javax.inject.Inject

/**
 * A REST implementation for [RemoteGitHubDataSource]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class RestGitHubDataSource @Inject constructor(private val gitHubService: GitHubService) :
    RemoteGitHubDataSource {
    override fun listRepos(user: String): Single<List<RepositoryEntity>> {
        return gitHubService.listRepos(user)
    }
}