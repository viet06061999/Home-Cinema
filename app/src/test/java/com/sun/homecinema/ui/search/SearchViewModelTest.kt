package com.sun.homecinema.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.homecinema.data.model.SearchResponse
import com.sun.homecinema.data.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(JUnit4::class)
class SearchViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var movieRepository: MovieRepository = mockk()

    private  var viewModel: SearchViewModel? = null

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        viewModel = SearchViewModel(movieRepository)
    }

    @Test
    fun `search should return error when internet is unavailable`() {
        val expected = "Internet unavailable"
        every {
            movieRepository.search("secret")
        } returns Observable.error(Exception("Internet unavailable"))
        viewModel?.search("secret")
        viewModel?.errorException?.observeForever {}
        Assert.assertEquals(expected, viewModel?.errorException?.value)
    }

    @Test
    fun `search should return  list search response`() {
        val expected = listOf(SearchResponse(1, "person", "Trấn Thành"))
        every {
            movieRepository.search("tran thanh")
        } returns Observable.just(listOf(SearchResponse(1, "person", "Trấn Thành")))
        viewModel?.search("tran thanh")
        viewModel?.search?.observeForever {}
        Assert.assertEquals(expected, viewModel?.search?.value)
    }

    @Test
    fun `search should return empty list search response`() {
        val expected = emptyList<SearchResponse>()
        every {
            movieRepository.search("")
        } returns Observable.just(emptyList())
        viewModel?.search("")
        viewModel?.search?.observeForever {}
         Assert.assertEquals(expected, viewModel?.search?.value)
//        assert(viewModel?.search?.value == expected)
    }

    @After
    fun refresh(){
        viewModel = null
    }
}
