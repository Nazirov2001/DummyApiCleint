package uz.bdm.dummyapiclient.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

import uz.bdm.dummyapiclient.model.*

interface Api {

    @Headers("app-id: 62e3a89ba328a78590131e47")
    @GET("user")
    fun getUsers(): Observable<BaseResponse<List<UserModel>>>

    @Headers("app-id: 62e3a89ba328a78590131e47")
    @GET("post")
    fun getPosts(): Observable<BaseResponse<List<PostModel>>>

    @Headers("app-id: 62e3a89ba328a78590131e47")
    @GET("user/{id}/post")
    fun getUserPosts(@Path("id") id: String): Observable<BaseResponse<List<PostModel>>>

    @Headers("app-id: 62e3a89ba328a78590131e47")
    @GET("user/{id}")
    fun getUserInfo(@Path("id") id: String): Observable<UserInfoModel>

    @Headers("app-id: 62e3a89ba328a78590131e47")
    @GET("post/{id}/comment")
    fun getComment(@Path("id") id: String): Observable<BaseResponse<List<CommentModel>>>


}