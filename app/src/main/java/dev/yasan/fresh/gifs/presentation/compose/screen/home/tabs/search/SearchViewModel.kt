package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.giphy.Gif
import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val gifRepository: GifRepository
) : ViewModel() {

    private var _queriedGifs = MutableLiveData<Resource<List<Gif>>>(Resource.Initial())
    val queriedGifs: LiveData<Resource<List<Gif>>> get() = _queriedGifs

    private var _trendingGifs = MutableLiveData<Resource<List<Gif>>>(Resource.Initial())
    val trendingGifs: LiveData<Resource<List<Gif>>> get() = _trendingGifs

    private var loadTrendingJob: Job? = null

    fun loadTrendingGifs() {
        loadTrendingJob?.cancel()
        loadTrendingJob = viewModelScope.launch(dispatchers.io) {
            _trendingGifs.postValue(Resource.Loading())
            val data: List<Gif> = gifRepository.getTrendingGifs()
            // TODO : handle errors
            _trendingGifs.postValue(Resource.Success(data))
        }
    }

}
