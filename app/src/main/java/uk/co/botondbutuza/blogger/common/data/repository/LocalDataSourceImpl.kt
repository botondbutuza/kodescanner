package uk.co.botondbutuza.blogger.common.data.repository

import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject
import uk.co.botondbutuza.blogger.common.data.LocalDataSource
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User


class LocalDataSourceImpl(private val realm: Realm) : LocalDataSource {

    override fun upsert(item: RealmObject) {
        realm.beginTransaction()
        realm.insertOrUpdate(item)
        realm.commitTransaction()
    }

    override fun upsert(items: List<RealmObject>) {
        realm.beginTransaction()
        realm.insertOrUpdate(items)
        realm.commitTransaction()
    }


    override fun posts(): Maybe<List<Post>> {
        val posts = realm.where(Post::class.java).findAll()

        return when (posts.isEmpty()) {
            true -> Maybe.never()
            else -> Maybe.just(posts)
        }
    }

    override fun post(id: Int): Single<Post> {
        val post = realm.where(Post::class.java).equalTo("id", id).findFirst()

        return when (post == null) {
            true -> Single.error(Exception("Post with id $id not found."))
            else -> Single.just(post)
        }
    }


    override fun user(id: Int): Maybe<User> {
        val user = realm.where(User::class.java).equalTo("id", id).findFirst()

        return when (user == null) {
            true -> Maybe.never()
            else -> Maybe.just(user)
        }
    }


    override fun comments(postId: Int): Maybe<List<Comment>> {
        val comments = realm.where(Comment::class.java).equalTo("postId", postId).findAll()

        return when (comments.isEmpty()) {
            true -> Maybe.never()
            else -> Maybe.just(comments)
        }
    }
}
