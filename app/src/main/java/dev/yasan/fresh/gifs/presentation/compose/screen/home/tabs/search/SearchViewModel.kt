package dev.yasan.fresh.gifs.presentation.compose.screen.home.tabs.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.fresh.gifs.data.source.network.NetworkConstants
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [SearchTab].
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val gifRepository: GifRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "SearchViewModel"
    }

    private var _queriedGifs = MutableLiveData<Resource<List<FlatGif>>>(Resource.Initial())
    val queriedGifs: LiveData<Resource<List<FlatGif>>> get() = _queriedGifs

    private var _trendingGifs = MutableLiveData<Resource<List<FlatGif>>>(Resource.Initial())
    val trendingGifs: LiveData<Resource<List<FlatGif>>> get() = _trendingGifs

    private var loadTrendingJob: Job? = null

    /**
     * Loads trending GIFs.
     *
     * @see trendingGifs
     */
    fun loadTrendingGifs(isRetry: Boolean = false) {
        Log.d(TAG, "loadTrendingGifs: called")
        loadTrendingJob?.cancel()
        loadTrendingJob = viewModelScope.launch(dispatchers.io) {
            _trendingGifs.postValue(Resource.Loading())
            val data = gifRepository.getTrendingGifs()
            if (isRetry && data is Resource.Error) {
                delay(NetworkConstants.FAKE_RETRY_DELAY)
            }
            _trendingGifs.postValue(data)
        }
    }

    private var loadQueriedJob: Job? = null

    /**
     * Loads GIFs for the given query.
     *
     * @see queriedGifs
     */
    fun loadQueriedGifs(query: String, isRetry: Boolean = false) {

        if (query.isNotBlank()) {

            Log.d(TAG, "loadQueriedGifs: fetching gifs for query: $query")

            loadQueriedJob?.cancel()
            loadQueriedJob = viewModelScope.launch(dispatchers.io) {
                _queriedGifs.postValue(Resource.Loading())
                val data = gifRepository.getQueriedGifs(query = query.trim())
                if (isRetry && data is Resource.Error) {
                    delay(NetworkConstants.FAKE_RETRY_DELAY)
                }
                _queriedGifs.postValue(data)
            }

        } else {
            Log.d(TAG, "loadQueriedGifs: query is blank, not fetching gifs")
        }

    }

    override fun onCleared() {
        loadTrendingJob?.cancel()
        loadQueriedJob?.cancel()
        super.onCleared()
    }

}
