package dev.yasan.fresh.gifs.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yasan.fresh.gifs.data.source.database.dao.FavoriteGifDao
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import javax.inject.Inject

/**
 * Room database abstract class for favorites database.
 *
 * @see FlatGif
 */
@Database(entities = [FlatGif::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoriteGifDao(): FavoriteGifDao

    class CallBack @Inject constructor() : RoomDatabase.Callback()

}
