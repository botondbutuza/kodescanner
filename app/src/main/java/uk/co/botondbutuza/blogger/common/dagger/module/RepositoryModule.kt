package uk.co.botondbutuza.blogger.common.dagger.module

import dagger.Module
import dagger.Provides
import io.reactivex.plugins.RxJavaPlugins
import uk.co.botondbutuza.blogger.common.dagger.scope.Local
import uk.co.botondbutuza.blogger.common.dagger.scope.Remote
import uk.co.botondbutuza.blogger.common.data.LocalDataSource
import uk.co.botondbutuza.blogger.common.data.RemoteDataSource
import uk.co.botondbutuza.blogger.common.data.repository.BlogDataRepository
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides @Singleton
    internal fun provideRepository(@Local local: LocalDataSource, @Remote remote: RemoteDataSource): BlogDataRepository {

        /*
         * Okay, so, if a stream is disposed before an exception can be thrown, Rx will *helpfully*
         * catch it and re-throw it wrapped in an UndeliverableException. So this call, which has to
         * go before any Rx call is made, swallows any such UndeliverableException.
         */
        RxJavaPlugins.setErrorHandler { _ -> }

        return BlogDataRepository(local, remote)
    }
}
