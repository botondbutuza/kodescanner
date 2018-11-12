package uk.co.botondbutuza.blogger.ui.main

import dagger.Component
import uk.co.botondbutuza.blogger.common.dagger.component.RepositoryComponent
import uk.co.botondbutuza.blogger.common.dagger.scope.ActivityScope

@ActivityScope
@Component(dependencies = [RepositoryComponent::class], modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
}