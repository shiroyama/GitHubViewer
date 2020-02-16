package us.shiroyama.githubviewer.infrastructure.rest.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import us.shiroyama.githubviewer.infrastructure.entity.RepositoryEntity

/**
 * A REST interface for GitHub
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<RepositoryEntity>>
}