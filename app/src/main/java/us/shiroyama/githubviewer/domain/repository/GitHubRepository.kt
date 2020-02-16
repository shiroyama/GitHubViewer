package us.shiroyama.githubviewer.domain.repository

import io.reactivex.Single
import us.shiroyama.githubviewer.domain.model.RepositoryModel

/**
 * Repository for GitHub Repositories
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface GitHubRepository {
    fun listRepos(user: String): Single<List<RepositoryModel>>
}