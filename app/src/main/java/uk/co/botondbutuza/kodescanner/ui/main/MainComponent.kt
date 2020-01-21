package uk.co.botondbutuza.kodescanner.ui.main

import dagger.Component
import uk.co.botondbutuza.kodescanner.common.dagger.component.RepositoryComponent
import uk.co.botondbutuza.kodescanner.common.dagger.scope.ActivityScope

@ActivityScope
@Component(dependencies = [RepositoryComponent::class], modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
}