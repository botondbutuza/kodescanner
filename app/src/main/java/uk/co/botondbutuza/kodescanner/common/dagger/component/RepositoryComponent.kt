package uk.co.botondbutuza.kodescanner.common.dagger.component

import android.app.Activity
import android.content.Context
import dagger.Component
import uk.co.botondbutuza.kodescanner.common.dagger.module.AppModule
import uk.co.botondbutuza.kodescanner.common.dagger.module.RemoteDataSourceModule
import uk.co.botondbutuza.kodescanner.common.dagger.module.RepositoryModule
import uk.co.botondbutuza.kodescanner.common.data.repository.FlightsRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, RemoteDataSourceModule::class])
interface RepositoryComponent {

    val repository: FlightsRepository

    val context: Context

    fun inject(activity: Activity)
}
