package uk.co.botondbutuza.blogger.common.dagger.module

import android.content.Context

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides internal fun provideContext() = context
}
