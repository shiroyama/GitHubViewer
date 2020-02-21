package us.shiroyama.githubviewer.domain.usecase.github

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.After
import org.junit.Before
import org.junit.Test
import us.shiroyama.githubviewer.domain.repository.GitHubRepository
import us.shiroyama.githubviewer.domain.usecase.ObserveSchedulerProvider
import us.shiroyama.githubviewer.domain.usecase.SubscribeSchedulerProvider

class GetRepositoriesTest {
    private lateinit var subscribeSchedulerProvider: SubscribeSchedulerProvider
    private lateinit var observeSchedulerProvider: ObserveSchedulerProvider
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var gitHubRepository: GitHubRepository
    private lateinit var getRepositories: GetRepositories

    @Before
    fun setUp() {
        subscribeSchedulerProvider = mock()
        observeSchedulerProvider = mock()
        compositeDisposable = mock()
        gitHubRepository = mock()
        whenever(subscribeSchedulerProvider.provide()).thenReturn(Schedulers.trampoline())
        whenever(observeSchedulerProvider.provide()).thenReturn(Schedulers.trampoline())
        whenever(gitHubRepository.listRepos(any())).thenReturn(Single.just(emptyList()))

        getRepositories = GetRepositories(
            subscribeSchedulerProvider,
            observeSchedulerProvider,
            compositeDisposable,
            gitHubRepository
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun executeSuccess() {
        getRepositories.execute(
            onNext = {
                assertThat(it).isEmpty()
                verify(subscribeSchedulerProvider, times(1)).provide()
                verify(observeSchedulerProvider, times(1)).provide()
                verify(gitHubRepository, times(1)).listRepos(any())
            },
            onError = {
                fail("not come here")
            },
            params = GetRepositories.Params("shiroyama")
        )
    }
}