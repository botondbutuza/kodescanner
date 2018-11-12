package uk.co.botondbutuza.blogger.common.data

import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.RealmObject
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User


/**
 * Created by brotond on 26/01/2018.
 */

interface LocalDataSource {

    fun upsert(item: RealmObject)

    fun upsert(items: List<RealmObject>)


    fun posts(): Maybe<List<Post>>

    fun post(id: Int): Single<Post>


    fun user(id: Int): Maybe<User>


    fun comments(postId: Int): Maybe<List<Comment>>
}
