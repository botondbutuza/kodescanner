package uk.co.botondbutuza.blogger.ui.post

import dagger.Component
import uk.co.botondbutuza.blogger.common.dagger.component.RepositoryComponent
import uk.co.botondbutuza.blogger.common.dagger.scope.ActivityScope

@ActivityScope
@Component(dependencies = [RepositoryComponent::class], modules = [PostDetailModule::class])
interface PostDetailComponent {

    fun inject(activity: PostDetailActivity)
}