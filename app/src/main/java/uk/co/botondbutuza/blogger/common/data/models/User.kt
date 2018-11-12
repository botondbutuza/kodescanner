package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var address: UserAddress? = null,
    var phone: String = "",
    var website: String = "",
    var company: UserCompany? = null
) : RealmObject()