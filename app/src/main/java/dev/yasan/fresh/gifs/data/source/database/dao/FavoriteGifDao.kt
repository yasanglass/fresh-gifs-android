package dev.yasan.fresh.gifs.data.source.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.yasan.fresh.gifs.data.source.database.FavoritesDatabase
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif

/**
 * [FlatGif] data access object (DAO).
 *
 * @see FavoritesDatabase
 */
@Dao
interface FavoriteGifDao {

    @Query("SELECT * FROM favorite_gifs ORDER BY date DESC")
    fun getFavorites(): LiveData<List<FlatGif>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flatGif: FlatGif)

    @Delete
    suspend fun delete(flatGif: FlatGif)

    @Query("DELETE FROM favorite_gifs")
    fun deleteAll()

}
