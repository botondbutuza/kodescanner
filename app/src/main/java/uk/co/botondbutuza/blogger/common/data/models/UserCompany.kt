package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject

open class UserCompany(
    var name: String = "",
    var catchPhrase: String = "",
    var bs: String = ""
) : RealmObject()