package dev.yasan.fresh.gifs.domain.usecase.trending

import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import dev.yasan.kit.core.Resource
import javax.inject.Inject

/**
 * Use case for getting trending gifs.
 */
class GetTrendingGifsUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {

    suspend operator fun invoke(): Resource<List<FlatGif>> {
        return gifRepository.getTrendingGifs()
    }

}