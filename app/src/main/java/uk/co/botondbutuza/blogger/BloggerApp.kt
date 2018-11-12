package uk.co.botondbutuza.blogger

import android.app.Application
import uk.co.botondbutuza.blogger.common.dagger.component.DaggerRepositoryComponent
import uk.co.botondbutuza.blogger.common.dagger.component.RepositoryComponent
import uk.co.botondbutuza.blogger.common.dagger.module.AppModule
import uk.co.botondbutuza.blogger.common.dagger.module.LocalDataSourceModule
import uk.co.botondbutuza.blogger.common.dagger.module.RemoteDataSourceModule

class BloggerApp : Application() {

    internal lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate() {
        super.onCreate()

        repositoryComponent = DaggerRepositoryComponent.builder()
            .appModule(AppModule(this))
            .localDataSourceModule(LocalDataSourceModule())
            .remoteDataSourceModule(RemoteDataSourceModule())
            .build()
    }
}