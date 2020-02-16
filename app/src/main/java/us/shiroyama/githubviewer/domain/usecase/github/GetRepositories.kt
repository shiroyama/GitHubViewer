package us.shiroyama.githubviewer.domain.usecase.github

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import us.shiroyama.githubviewer.domain.model.RepositoryModel
import us.shiroyama.githubviewer.domain.repository.GitHubRepository
import us.shiroyama.githubviewer.domain.usecase.ObserveSchedulerProvider
import us.shiroyama.githubviewer.domain.usecase.SingleUseCase
import us.shiroyama.githubviewer.domain.usecase.SubscribeSchedulerProvider
import javax.inject.Inject

/**
 * A [SingleUseCase] for getting repositories from GitHub
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class GetRepositories @Inject constructor(
    subscribeSchedulerProvider: SubscribeSchedulerProvider,
    observeSchedulerProvider: ObserveSchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val gitHubRepository: GitHubRepository
) : SingleUseCase<List<RepositoryModel>, GetRepositories.Params>(
    subscribeSchedulerProvider,
    observeSchedulerProvider,
    compositeDisposable
) {
    override fun buildObservable(params: Params): Single<List<RepositoryModel>> {
        return gitHubRepository.listRepos(params.user)
    }

    data class Params(val user: String)

}