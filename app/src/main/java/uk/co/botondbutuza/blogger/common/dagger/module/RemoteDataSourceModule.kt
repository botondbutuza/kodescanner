package uk.co.botondbutuza.blogger.common.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.botondbutuza.blogger.common.dagger.scope.Remote
import uk.co.botondbutuza.blogger.common.data.BlogContentApi
import uk.co.botondbutuza.blogger.common.data.RemoteDataSource
import uk.co.botondbutuza.blogger.common.data.repository.RemoteDataSourceImpl
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Provides @Remote @Singleton
    internal fun provideDataSource(blogContentApi: BlogContentApi): RemoteDataSource = RemoteDataSourceImpl(blogContentApi)

    @Provides @Singleton
    internal fun provideGson(): Gson = GsonBuilder().create()

    @Provides @Singleton
    internal fun provideServerInterface(gson: Gson, okHttpClient: OkHttpClient): BlogContentApi = getServerInterface(getRetrofit(gson, okHttpClient))

    @Provides @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    private fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getServerInterface(retrofit: Retrofit): BlogContentApi = retrofit.create(BlogContentApi::class.java)


    companion object {
        private const val API_ENDPOINT = "http://jsonplaceholder.typicode.com/"
    }
}
