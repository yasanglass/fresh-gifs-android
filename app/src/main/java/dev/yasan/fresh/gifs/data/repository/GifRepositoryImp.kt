package dev.yasan.fresh.gifs.data.repository

import android.util.Log
import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.data.source.network.GiphyAPI
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.Resource
import java.io.IOException
import javax.inject.Inject

class GifRepositoryImp @Inject constructor(
    private val giphyAPI: GiphyAPI
) : GifRepository {

    companion object {
        private const val TAG = "GifRepositoryImp"
    }

    override suspend fun getTrendingGifs(): Resource<List<FlatGif>> {
        return try {
            val response = giphyAPI.fetchTrendingGifs()

            if (response.isSuccessful) {
                val validGifs = response.body()?.data?.filter { it.isValid() } ?: emptyList()
                val flatValidGifs = validGifs.map { it.flatten() }
                // Removing duplicate items because duplicate ids can cause issues
                // GIPHY API returns duplicate GIFs sometimes, probably a bug
                Resource.Success(data = flatValidGifs.distinct())
            } else {
                Resource.Error(messageResourceId = R.string.error_unsuccessful)
            }

        } catch (e: IOException) {
            Log.d(TAG, "getTrendingGifs: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_network)
        } catch (e: Exception) {
            Log.d(TAG, "getTrendingGifs: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_generic)
        }

    }

    override suspend fun getQueriedGifs(query: String): Resource<List<FlatGif>> {
        return try {
            val response = giphyAPI.fetchQueriedGifs(query = query)

            if (response.isSuccessful) {
                val validGifs = response.body()?.data?.filter { it.isValid() } ?: emptyList()
                val flatValidGifs = validGifs.map { it.flatten() }
                // Removing duplicate items because duplicate ids can cause issues
                // GIPHY API returns duplicate GIFs sometimes, probably a bug
                Resource.Success(data = flatValidGifs.distinct())
            } else {
                Resource.Error(messageResourceId = R.string.error_unsuccessful)
            }

        } catch (e: IOException) {
            Log.d(TAG, "getTrendingGifs: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_network)
        } catch (e: Exception) {
            Log.d(TAG, "getTrendingGifs: ${e.message}")
            Resource.Error(messageResourceId = R.string.error_generic)
        }

    }

}