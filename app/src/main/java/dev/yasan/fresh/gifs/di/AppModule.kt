package dev.yasan.fresh.gifs.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yasan.fresh.gifs.data.repository.FavoriteRepositoryImp
import dev.yasan.fresh.gifs.data.repository.GifRepositoryImp
import dev.yasan.fresh.gifs.data.source.database.FavoritesDatabase
import dev.yasan.fresh.gifs.data.source.database.dao.FavoriteGifDao
import dev.yasan.fresh.gifs.data.source.network.GiphyAPI
import dev.yasan.fresh.gifs.domain.repository.FavoriteRepository
import dev.yasan.fresh.gifs.domain.repository.GifRepository
import dev.yasan.kit.core.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyAPI(retrofit: Retrofit): GiphyAPI =
        retrofit.create(GiphyAPI::class.java)

    @Singleton
    @Provides
    fun provideGifRepository(giphyAPI: GiphyAPI): GifRepository =
        GifRepositoryImp(giphyAPI = giphyAPI)

    @Singleton
    @Provides
    fun provideFavoriteRepository(favoriteGifDao: FavoriteGifDao): FavoriteRepository =
        FavoriteRepositoryImp(favoriteGifDao = favoriteGifDao)

    @Singleton
    @Provides
    fun provideFavoritesDatabase(
        app: Application,
        callback: FavoritesDatabase.CallBack
    ) = Room.databaseBuilder(app, FavoritesDatabase::class.java, "favorites")
        .addCallback(callback)
        .build()

    @Provides
    fun provideFavoriteGifDao(favoritesDatabase: FavoritesDatabase) = favoritesDatabase.favoriteGifDao()

}
