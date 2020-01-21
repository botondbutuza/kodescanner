package uk.co.botondbutuza.kodescanner

import android.app.Application
import uk.co.botondbutuza.kodescanner.common.dagger.component.DaggerRepositoryComponent
import uk.co.botondbutuza.kodescanner.common.dagger.component.RepositoryComponent
import uk.co.botondbutuza.kodescanner.common.dagger.module.AppModule
import uk.co.botondbutuza.kodescanner.common.dagger.module.RemoteDataSourceModule

class KodeScannerApp : Application() {

    internal lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate() {
        super.onCreate()

        repositoryComponent = DaggerRepositoryComponent.builder()
            .appModule(AppModule(this))
            .remoteDataSourceModule(RemoteDataSourceModule())
            .build()
    }
}