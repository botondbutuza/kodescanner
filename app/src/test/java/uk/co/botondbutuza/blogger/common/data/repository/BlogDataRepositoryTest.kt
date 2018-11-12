package uk.co.botondbutuza.blogger.common.data.repository

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.botondbutuza.blogger.common.data.LocalDataSource
import uk.co.botondbutuza.blogger.common.data.RemoteDataSource
import uk.co.botondbutuza.blogger.common.data.models.Comment
import uk.co.botondbutuza.blogger.common.data.models.Post
import uk.co.botondbutuza.blogger.common.data.models.User
import java.lang.Exception

class BlogDataRepositoryTest {

    private lateinit var remote: RemoteDataSource
    private lateinit var local: LocalDataSource
    private lateinit var dataRepository: BlogDataRepository

    private val posts by lazy { listOf(Post(), Post(), Post()) }
    private val users by lazy { listOf(User(id = 4), User(id = 8), User(id = 15), User(id = 16), User(id = 23), User(id = 42)) }
    private val comments by lazy { listOf(Comment(postId = 108), Comment(), Comment(postId = 108)) }
    private val post by lazy { Post() }
    private val user by lazy { User(id = 23) }
    private val comment by lazy { Comment(postId = 108) }


    @Before
    fun setUp() {
        remote = mock()
        local = mock()
        dataRepository = BlogDataRepository(local, remote)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPosts_remoteFirst() {
        whenever(local.posts()).thenReturn(Maybe.never())
        whenever(remote.posts()).thenReturn(Single.just(posts))

        dataRepository
            .getPosts()
            .test()
            .assertComplete()
            .assertValue(posts)
    }

    @Test
    fun getPosts_localFirst() {
        whenever(local.posts()).thenReturn(Maybe.just(posts))
        whenever(remote.posts()).thenReturn(Single.never())

        dataRepository
            .getPosts()
            .test()
            .assertComplete()
            .assertValues(posts)
    }

    @Test
    fun getPostsRemote_error() {
        whenever(remote.posts()).thenReturn(Single.never())

        dataRepository
            .getPostsRemote()
            .test()
            .assertNotComplete()
    }

    @Test
    fun getPostsRemote() {
        whenever(remote.posts()).thenReturn(Single.just(posts))

        dataRepository
            .getPostsRemote()
            .test()
            .assertComplete()
            .assertValues(posts)
    }

    @Test
    fun getPost_error() {
        whenever(local.post(any())).thenReturn(Single.error(Exception("No element")))

        dataRepository
            .getPost(any())
            .test()
            .assertNotComplete()
            .assertErrorMessage("No element")
    }

    @Test
    fun getPost() {
        whenever(local.post(any())).thenReturn(Single.just(post))

        dataRepository
            .getPost(any())
            .test()
            .assertComplete()
            .assertValue(post)
    }

    @Test
    fun getUser_localFirst() {
        whenever(local.user(any())).thenReturn(Maybe.just(user))
        whenever(remote.users()).thenReturn(Single.never())

        dataRepository
            .getUser(any())
            .test()
            .assertComplete()
            .assertValue(user)
    }

    @Test
    fun getUser_remoteFirst() {
        whenever(local.user(user.id)).thenReturn(Maybe.never())
        whenever(remote.users()).thenReturn(Single.just(users))

        dataRepository
            .getUser(user.id)
            .test()
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.id == user.id }
    }

    @Test
    fun getComments() {
        whenever(local.comments(comment.postId)).thenReturn(Maybe.never())
        whenever(remote.comments()).thenReturn(Single.just(comments))

        dataRepository
            .getComments(comment.postId)
            .test()
            .assertComplete()
            .assertValue {
                it.forEach {
                    if (it.postId != comment.postId) {
                        return@assertValue false
                    }
                }
                return@assertValue true
            }
    }
}