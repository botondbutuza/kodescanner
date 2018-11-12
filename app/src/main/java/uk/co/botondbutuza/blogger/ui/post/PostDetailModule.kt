package uk.co.botondbutuza.blogger.ui.post

import dagger.Module
import dagger.Provides

@Module
class PostDetailModule(private val view: PostDetailContract.View) {

    @Provides fun provideView() = view
}