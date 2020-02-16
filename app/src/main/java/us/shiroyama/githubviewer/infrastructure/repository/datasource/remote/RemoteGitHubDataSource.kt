package us.shiroyama.githubviewer.infrastructure.repository.datasource.remote

import io.reactivex.Single
import us.shiroyama.githubviewer.infrastructure.entity.RepositoryEntity

/**
 * An interface for remote GitHub data source
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface RemoteGitHubDataSource {
    fun listRepos(user: String): Single<List<RepositoryEntity>>
}