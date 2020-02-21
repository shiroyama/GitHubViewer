package us.shiroyama.githubviewer.infrastructure.repository

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import us.shiroyama.githubviewer.infrastructure.entity.mapper.RepositoryMapper
import us.shiroyama.githubviewer.infrastructure.repository.datasource.remote.RemoteGitHubDataSource

class GitHubInfraRepositoryTest {
    private val dataSource: RemoteGitHubDataSource = mock(name = "RemoteGitHubDataSource")
    private val mapper = spy(RepositoryMapper())
    private val repository = GitHubInfraRepository(dataSource, mapper)

    @Before
    fun setUp() {
        whenever(dataSource.listRepos(any())).thenReturn(Single.just(emptyList()))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun listRepos() {
        repository.listRepos("shiroyama")
            .test()
            .await()

        verify(dataSource, times(1)).listRepos(any())
        verify(mapper, times(1)).convert(any())
    }
}