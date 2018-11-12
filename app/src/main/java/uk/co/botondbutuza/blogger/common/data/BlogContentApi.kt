package uk.co.botondbutuza.blogger.common.data

import io.reactivex.Single
import retrofit2.http.GET
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User

interface BlogContentApi {

    @GET("posts")
    fun posts(): Single<List<Post>>

    @GET("users")
    fun users(): Single<List<User>>

    @GET("comments")
    fun comments(): Single<List<Comment>>

}
