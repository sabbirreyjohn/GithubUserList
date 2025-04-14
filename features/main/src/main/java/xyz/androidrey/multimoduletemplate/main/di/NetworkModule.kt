package xyz.androidrey.multimoduletemplate.main.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import xyz.androidrey.multimoduletemplate.main.BuildConfig
import xyz.androidrey.multimoduletemplate.main.data.source.local.TheDatabase
import xyz.androidrey.multimoduletemplate.main.data.source.remote.UserRemoteMediator
import xyz.androidrey.multimoduletemplate.main.domain.repository.DataRepository
import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.main.data.repository.DataRepositoryImpl
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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TheDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TheDatabase::class.java,
            "products.db"
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUserPager(database: TheDatabase, requestHandler: RequestHandler): Pager<Int, Product> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 15,
                initialLoadSize = 30,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(database, requestHandler),
            pagingSourceFactory = {
                database.productDao.getProductPaging()
            }
        )

    }
}