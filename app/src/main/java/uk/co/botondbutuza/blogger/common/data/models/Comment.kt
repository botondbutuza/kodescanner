package uk.co.botondbutuza.blogger.common.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Comment(
    @PrimaryKey var id: Int = 0,
    var postId: Int = 0,
    var name: String = "",
    var email: String = "",
    var body: String = ""
): RealmObject()