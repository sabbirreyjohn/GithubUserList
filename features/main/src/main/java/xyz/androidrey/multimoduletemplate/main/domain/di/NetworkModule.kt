package xyz.androidrey.multimoduletemplate.main.domain.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.androidrey.multimoduletemplate.main.BuildConfig
import xyz.androidrey.multimoduletemplate.main.data.network.ApiHelper
import xyz.androidrey.multimoduletemplate.main.data.network.ApiHelperImpl
import xyz.androidrey.multimoduletemplate.main.data.network.ApiInterface
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "httpResponses")
        val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024) // 10 MiB
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.base_url).build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(userApiHelperImpl: ApiHelperImpl): ApiHelper =
        userApiHelperImpl

}