package dev.yasan.fresh.gifs.domain.usecase.search

import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.Resource
import javax.inject.Inject

/**
 * Use case for getting queried gifs.
 */
class GetQueriedGifsUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {

    suspend operator fun invoke(query: String): Resource<List<FlatGif>> {
        return gifRepository.getQueriedGifs(query = query)
    }

}