package uk.co.botondbutuza.blogger.common.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.botondbutuza.blogger.common.data.BlogContentApi
import uk.co.botondbutuza.blogger.common.data.RemoteDataSource
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User

class RemoteDataSourceImpl(private val api: BlogContentApi) : RemoteDataSource {

    override fun posts(): Single<List<Post>> {
        return api
            .posts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun comments(): Single<List<Comment>> {
        return api
            .comments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun users(): Single<List<User>> {
        return api
            .users()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}