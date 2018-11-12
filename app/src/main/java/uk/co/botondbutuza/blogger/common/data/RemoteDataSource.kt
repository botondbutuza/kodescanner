package uk.co.botondbutuza.blogger.common.data

import io.reactivex.Single
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User


/**
 * Created by brotond on 26/01/2018.
 */

interface RemoteDataSource {

    fun posts(): Single<List<Post>>

    fun comments(): Single<List<Comment>>

    fun users(): Single<List<User>>
}
