package xyz.androidrey.multimoduletemplate.main.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import xyz.androidrey.multimoduletemplate.main.BuildConfig
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepositoryImpl
import xyz.androidrey.multimoduletemplate.network.http.HttpClientBuilder
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClientBuilder().protocol(URLProtocol.HTTPS).host(BuildConfig.base_url).build()

    @Provides
    @Singleton
    fun provideRequestHandler(client: HttpClient) = RequestHandler(client)
    @Singleton
    @Provides
    fun provideDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository {
        return dataRepositoryImpl
    }
}