package dev.yasan.fresh.gifs.data.repository

import dev.yasan.fresh.gifs.R
import dev.yasan.fresh.gifs.data.source.network.GiphyAPI
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.Resource
import okio.IOException
import javax.inject.Inject

class GifRepositoryImp @Inject constructor(
    private val giphyAPI: GiphyAPI
) : GifRepository {

    override suspend fun getTrendingGifs(): Resource<List<FlatGif>> {
        return try {
            val response = giphyAPI.fetchTrendingGifs()

            if (response.isSuccessful) {
                val validGifs=  response.body()?.data?.filter { it.isValid() } ?: emptyList()
                val flatValidGifs = validGifs.map { it.flatten() }
                Resource.Success(data = flatValidGifs)
            } else {
                Resource.Error(messageResourceId = R.string.failed_to_load_data)
            }
        } catch (e: IOException) {
            Resource.Error(messageResourceId = R.string.failed_to_load_data)
        }
    }

    override suspend fun getQueriedGifs(query: String): Resource<List<FlatGif>> {
        return try {
            val response = giphyAPI.fetchQueriedGifs(query = query)

            if (response.isSuccessful) {
                val validGifs=  response.body()?.data?.filter { it.isValid() } ?: emptyList()
                val flatValidGifs = validGifs.map { it.flatten() }
                Resource.Success(data = flatValidGifs)
            } else {
                Resource.Error(messageResourceId = R.string.failed_to_load_data)
            }
        } catch (e: IOException) {
            Resource.Error(messageResourceId = R.string.failed_to_load_data)
        }
    }

}