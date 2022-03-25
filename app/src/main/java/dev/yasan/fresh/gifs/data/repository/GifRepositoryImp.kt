package dev.yasan.fresh.gifs.data.repository

import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.data.source.network.GiphyAPI
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.giphy.Gif
import dev.yasan.kit.core.Resource
import javax.inject.Inject

class GifRepositoryImp @Inject constructor(
    private val giphyAPI: GiphyAPI
) : GifRepository {

    override suspend fun getTrendingGifs(): Resource<List<Gif>> {
        return try {
            val response = giphyAPI.fetchTrendingGifs()

            if (response.isSuccessful) {
                Resource.Success(
                    data = response.body()?.data?.filter { it.isValid() } ?: emptyList())
            } else {
                Resource.Error(messageResourceId = R.string.failed_to_load_data)
            }
        } catch (e: Exception) { // TODO catch a less generic exception
            Resource.Error(messageResourceId = R.string.failed_to_load_data)
        }
    }

    override suspend fun getQueriedGifs(query: String): Resource<List<Gif>> {
        return try {
            val response = giphyAPI.fetchQueriedGifs(query = query)

            if (response.isSuccessful) {
                Resource.Success(
                    data = response.body()?.data?.filter { it.isValid() } ?: emptyList())
            } else {
                Resource.Error(messageResourceId = R.string.failed_to_load_data)
            }
        } catch (e: Exception) { // TODO catch a less generic exception
            Resource.Error(messageResourceId = R.string.failed_to_load_data)
        }
    }

}