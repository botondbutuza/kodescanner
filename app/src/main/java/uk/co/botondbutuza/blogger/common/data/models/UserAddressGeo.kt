package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject

open class UserAddressGeo(
    var lat: String = "",
    var lng: String = ""
) : RealmObject()