package com.hoodlums.linkbucket.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hoodlums.linkbucket.BuildConfig
import com.hoodlums.linkbucket.data.local.SharedPreferenceManager
import com.hoodlums.linkbucket.data.remote.BucketServices
import com.hoodlums.linkbucket.data.remote.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(sharedPreferenceManager: SharedPreferenceManager): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val apiInterceptor = HttpLoggingInterceptor()
        apiInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)
        httpClient.authenticator((TokenAuthenticator(sharedPreferenceManager)))
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(apiInterceptor)
        }
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideTeconApiService(retrofit: Retrofit): BucketServices = retrofit.create(BucketServices::class.java)

    /*fun provideRemoteDataRepository(service: TeconApiService) = UserRepository(service)*/
}