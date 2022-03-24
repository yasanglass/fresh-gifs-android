package dev.yasan.fresh.gifs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yasan.fresh.gifs.data.network.GiphyAPI
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

}
