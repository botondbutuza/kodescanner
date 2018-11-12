package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post(
    @PrimaryKey var id: Int = 0,
    var userId: Int = 0,
    var title: String = "",
    var body: String = ""
) : RealmObject()