package uk.co.botondbutuza.blogger.common.dagger.component


import android.app.Activity
import android.content.Context
import dagger.Component
import uk.co.botondbutuza.blogger.common.dagger.module.AppModule
import uk.co.botondbutuza.blogger.common.dagger.module.LocalDataSourceModule
import uk.co.botondbutuza.blogger.common.dagger.module.RemoteDataSourceModule
import uk.co.botondbutuza.blogger.common.dagger.module.RepositoryModule
import uk.co.botondbutuza.blogger.common.data.repository.BlogDataRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, LocalDataSourceModule::class, RemoteDataSourceModule::class])
interface RepositoryComponent {

    val repository: BlogDataRepository

    val context: Context

    fun inject(activity: Activity)
}
