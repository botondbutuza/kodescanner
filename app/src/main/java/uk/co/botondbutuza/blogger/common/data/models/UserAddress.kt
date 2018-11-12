package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject

open class UserAddress(
    var street: String = "",
    var suite: String = "",
    var city: String = "",
    var zipcode: String = "",
    var geo: UserAddressGeo? = null
) : RealmObject()