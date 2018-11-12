package uk.co.botondbutuza.blogger.ui.main

import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainContract.View) {

    @Provides fun provideView() = view
}