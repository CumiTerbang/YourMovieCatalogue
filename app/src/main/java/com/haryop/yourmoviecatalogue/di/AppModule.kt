package com.haryop.yourmoviecatalogue.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.haryop.yourmoviecatalogue.data.local.AppDatabase
import com.haryop.yourmoviecatalogue.data.local.MovieDao
import com.haryop.yourmoviecatalogue.data.remote.ApiServices
import com.haryop.yourmoviecatalogue.data.remote.RemoteDataSource
import com.haryop.yourmoviecatalogue.data.repository.OMDbRepository
import com.haryop.yourmoviecatalogue.utils.ConstantsObj
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun logOkHttplient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(ConstantsObj.OMDB_BASEURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(logOkHttplient())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAPIService(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(newsApiOrgServices: ApiServices) =
        RemoteDataSource(newsApiOrgServices)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()


    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: MovieDao,
        remoteDataSource: RemoteDataSource,
        apiServices: ApiServices
    ) =
        OMDbRepository(localDataSource, remoteDataSource, apiServices)

}
