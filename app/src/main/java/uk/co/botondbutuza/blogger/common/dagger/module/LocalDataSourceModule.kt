package uk.co.botondbutuza.blogger.common.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.botondbutuza.blogger.common.dagger.scope.Local
import uk.co.botondbutuza.blogger.common.data.LocalDataSource
import uk.co.botondbutuza.blogger.common.data.repository.LocalDataSourceImpl

import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Provides @Local @Singleton
    internal fun provideDataSource(realm: Realm): LocalDataSource = LocalDataSourceImpl(realm)

    @Provides @Singleton
    internal fun provideRealm(config: RealmConfiguration): Realm {
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }

    @Provides @Singleton
    internal fun provideRealmConfiguration(context: Context): RealmConfiguration {
        Realm.init(context)

        return RealmConfiguration.Builder()
            .schemaVersion(DATABASE_VERSION)
            .build()
    }


    companion object {
        private const val DATABASE_VERSION = 1L
    }
}
