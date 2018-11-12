package uk.co.botondbutuza.blogger.common.dagger.scope

import javax.inject.Qualifier
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class Local
