package us.shiroyama.githubviewer.infrastructure.repository.datasource.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Fail.fail
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import us.shiroyama.githubviewer.infrastructure.entity.RepositoryEntity
import us.shiroyama.githubviewer.infrastructure.rest.service.GitHubService
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class RestGitHubDataSourceTest {
    private val mockWebServer = MockWebServer()

    private lateinit var restGitHubDataSource: RestGitHubDataSource

    /**
     * If you use [MockWebServer], you can easily sort out HTTP responses using OkHttp even in local unit test.
     * If you save the JSON response with the actual server in `test / resources`, you can perform the same test as in the production environment.
     */
    @Before
    @Throws(Exception::class)
    fun setUp() {
        val dispatcher: Dispatcher =
            object : Dispatcher() {
                @Throws(InterruptedException::class)
                override fun dispatch(request: RecordedRequest): MockResponse {
                    if (request.path == null) {
                        return MockResponse().setResponseCode(400)
                    }
                    if (request.path!!.matches("/users/srym/repos/?.*".toRegex())) {
                        return MockResponse().setBody(readJsonFromResources("users_srym_repos.json")!!)
                            .setResponseCode(200)
                    }
                    return if (request.path!!.matches("/users/ymnder/repos/?.*".toRegex())) {
                        MockResponse().setBody(readJsonFromResources("users_ymnder_repos.json")!!)
                            .setResponseCode(200)
                    } else MockResponse().setResponseCode(404)
                }
            }
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(OkHttpClient())
            .build()
        val gitHubService: GitHubService = retrofit.create(GitHubService::class.java)
        restGitHubDataSource = RestGitHubDataSource(gitHubService)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun getUserRepositories_inputSrym_returnsSuccessfulResults() {
        val repositories: List<RepositoryEntity> = restGitHubDataSource.listRepos("srym")
            .test()
            .await()
            .values()[0]

        assertThat(repositories)
            .isNotEmpty
            .hasSize(30)
        assertThat(repositories[0].name).isEqualTo("dotfiles")
    }

    @Test
    @Throws(Exception::class)
    fun getUserRepositories_inputYmnder_returnsSuccessfulResults() {
        val repositories: List<RepositoryEntity> = restGitHubDataSource.listRepos("ymnder")
            .test()
            .await()
            .values()[0]
        assertThat(repositories)
            .isNotEmpty
            .hasSize(23)
        assertThat(repositories[0].name).isEqualTo("conference-app-2018")
    }

    @Test
    @Throws(Exception::class)
    fun getUserRepositories_inputInvalidAccount_causedApiException() {
        restGitHubDataSource.listRepos("hoge")
            .test()
            .await()
            .assertNotComplete()
            .assertError(HttpException::class.java)
    }

    /**
     * Helper method that reads and returns JSON from the file name
     */
    private fun readJsonFromResources(fileName: String): String? {
        val inputStream =
            javaClass.classLoader!!.getResourceAsStream(fileName)
        val bufferedReader =
            BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        try {
            var buffer: String?
            while (bufferedReader.readLine().also { buffer = it } != null) {
                stringBuilder.append(buffer)
            }
        } catch (e: IOException) {
            fail<RuntimeException>(e.message, e)
        }
        return stringBuilder.toString()
    }
}