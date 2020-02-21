package us.shiroyama.githubviewer.presentation.presenter

import android.os.Bundle
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyList
import org.robolectric.RobolectricTestRunner
import us.shiroyama.githubviewer.RxImmediateSchedulerRule
import us.shiroyama.githubviewer.domain.repository.GitHubRepository
import us.shiroyama.githubviewer.domain.usecase.ObserveSchedulerProvider
import us.shiroyama.githubviewer.domain.usecase.SubscribeSchedulerProvider
import us.shiroyama.githubviewer.domain.usecase.github.GetRepositories
import us.shiroyama.githubviewer.presentation.mapper.RepoViewModelMapper
import us.shiroyama.githubviewer.presentation.view.contract.RepositoryListContract

@RunWith(RobolectricTestRunner::class)
class RepositoryListPresenterTest {
    @get:Rule
    val rule = RxImmediateSchedulerRule()

    lateinit var getRepositories: GetRepositories
    lateinit var mapper: RepoViewModelMapper
    lateinit var view: RepositoryListContract.View
    lateinit var presenter: RepositoryListPresenter

    @Before
    fun setUp() {
        val gitHubRepository: GitHubRepository = mock()
        val subscribeSchedulerProvider: SubscribeSchedulerProvider = mock()
        val observeSchedulerProvider: ObserveSchedulerProvider = mock()
        val compositeDisposable: CompositeDisposable = mock()
        whenever(gitHubRepository.listRepos(any())).thenReturn(Single.just(emptyList()))
        whenever(subscribeSchedulerProvider.provide()).thenReturn(Schedulers.trampoline())
        whenever(observeSchedulerProvider.provide()).thenReturn(Schedulers.trampoline())

        getRepositories = spy(
            GetRepositories(
                subscribeSchedulerProvider,
                observeSchedulerProvider,
                compositeDisposable,
                gitHubRepository
            )
        )

        mapper = spy(RepoViewModelMapper())
        view = mock()
        val bundle: Bundle = mock()
        whenever(bundle.getString(any())).thenReturn("shiroyama")
        whenever(view.getFragmentArguments()).thenReturn(bundle)
        presenter = RepositoryListPresenter(getRepositories, mapper)
        presenter.view = view
    }

    @After
    fun tearDown() {
    }

    @Test
    fun onViewCreated() {
        presenter.onViewCreated(null)
        verify(getRepositories, times(1)).execute(any(), any(), any())
        verify(view, times(1)).showList(any())
        verify(mapper, times(1)).convert(anyList())
    }
}