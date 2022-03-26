package dev.yasan.fresh.gifs.data.source.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import dev.yasan.fresh.gifs.data.source.database.FavoritesDatabase
import dev.yasan.fresh.gifs.getOrAwaitValue
import dev.yasan.fresh.gifs.model.freshgifs.FlatGif
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoriteGifDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testScope = TestScope()

    private lateinit var database: FavoritesDatabase
    private lateinit var dao: FavoriteGifDao
    private lateinit var gif: FlatGif

    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.favoriteGifDao()

        gif = FlatGif(
            id = "id",
            title = "You're Hired",
            url = "https://media1.giphy.com/media/l2Jehbz2HdLnTQSFa/giphy-preview.mp4?cid=3974a4b8wd6volezjgk0fsdrjxlgqnysd6v9ysfzcuve37xb&rid=giphy-preview.mp4&ct=g",
            width = 100,
            height = 100
        )

    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert() = testScope.runTest {
        dao.insert(gif)
        Truth.assertThat(dao.getFavorites().getOrAwaitValue()).isNotEmpty()
    }

    @Test
    fun update() = testScope.runTest {
        dao.insert(gif)
        val gif2 = gif.copy(title = "You're Not Hired")
        dao.insert(gif2)
        Truth.assertThat(dao.getFavorites().getOrAwaitValue()).contains(gif2)
        Truth.assertThat(dao.getFavorites().getOrAwaitValue().size).isEqualTo(1)
    }

    @Test
    fun delete() = testScope.runTest {
        dao.insert(gif)
        dao.delete(gif)
        Truth.assertThat(dao.getFavorites().getOrAwaitValue()).isEmpty()
    }

    @Test
    fun deleteAll() = testScope.runTest {
        dao.insert(gif.copy(id = "id1"))
        dao.insert(gif.copy(id = "id2"))
        dao.deleteAll()
        Truth.assertThat(dao.getFavorites().getOrAwaitValue()).isEmpty()
    }

}