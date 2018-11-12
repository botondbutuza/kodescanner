package uk.co.botondbutuza.blogger.common.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import uk.co.botondbutuza.blogger.common.dagger.scope.Local
import uk.co.botondbutuza.blogger.common.dagger.scope.Remote
import uk.co.botondbutuza.blogger.common.data.LocalDataSource
import uk.co.botondbutuza.blogger.common.data.RemoteDataSource
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User


class BlogDataRepository(
    @param:Local @field:Local private val localDataSource: LocalDataSource,
    @param:Remote @field:Remote private val remoteDataSource: RemoteDataSource
) {

    fun getPosts(): Single<List<Post>> {
        return Single.amb(listOf(
            localDataSource.posts().toSingle(),
            getPostsRemote()
        ))
    }

    fun getPostsRemote(): Single<List<Post>> {
        return remoteDataSource
            .posts()
            // when we got it, save it
            .doOnSuccess(localDataSource::upsert)
    }

    fun getPost(postId: Int): Single<Post> {
        return localDataSource
            // since we save every post during the batch get, single posts must be in local
            .post(postId)
    }


    fun getUser(id: Int): Single<User> {
        return Single.amb(listOf(
            // try local
            localDataSource
                .user(id)
                .toSingle(),
            // default to remote
            remoteDataSource
                .users()
                // plop it into storage
                .doOnSuccess(localDataSource::upsert)
                // flatten to elements
                .flatMapObservable { Observable.fromIterable(it) }
                // only care about the one with `id`
                .filter { it.id == id }
                // if it ain't there, we got big problems
                .firstOrError()
        ))
    }

    fun getComments(postId: Int): Single<List<Comment>> {
        return Single.amb(listOf(
            // try local
            localDataSource
                .comments(postId)
                .toSingle(),
            // default to remote
            remoteDataSource
                .comments()
                // plop it into storage
                .doOnSuccess(localDataSource::upsert)
                // flatten
                .flatMapObservable { Observable.fromIterable(it) }
                // gather only the ones pertaining to the current post
                .filter { it.postId == postId }
                .toList()
        ))
    }
}
