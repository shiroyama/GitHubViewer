package us.shiroyama.githubviewer.infrastructure.repository

import io.reactivex.Single
import us.shiroyama.githubviewer.domain.model.RepositoryModel
import us.shiroyama.githubviewer.domain.repository.GitHubRepository
import us.shiroyama.githubviewer.infrastructure.entity.mapper.RepositoryMapper
import us.shiroyama.githubviewer.infrastructure.repository.datasource.remote.RemoteGitHubDataSource
import javax.inject.Inject

/**
 * Repository for GitHub in infrastructure layer
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class GitHubInfraRepository @Inject constructor(
    private val remoteDataSource: RemoteGitHubDataSource,
    private val mapper: RepositoryMapper
) : GitHubRepository {
    override fun listRepos(user: String): Single<List<RepositoryModel>> {
        return remoteDataSource
            .listRepos(user)
            .map { mapper.convert(it) }
    }
}