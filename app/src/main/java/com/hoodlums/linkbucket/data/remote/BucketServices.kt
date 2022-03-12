package com.hoodlums.linkbucket.data.remote

import com.hoodlums.linkbucket.data.models.AuthPostBody
import com.hoodlums.linkbucket.data.models.LinkPostBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BucketServices {

    // Authorization | Authentication

    @POST("auth/login")
    suspend fun signIn(@Body body: AuthPostBody)

    @POST("auth/signup")
    suspend fun signUp(@Body body: AuthPostBody)

    @GET("auth/me")
    suspend fun getUser()

    @POST("auth/logout")
    suspend fun signOut()

    //Bucket
    @POST("bucket/links/create")
    suspend fun createLink(@Body body: LinkPostBody)

    @GET("bucket/links/fetch")
    suspend fun fetchLinks()

    @GET("bucket/i/{id}")
    suspend fun openLink(@Path("id") linkID: String)

    @POST("bucket/link/{id}/upvote/")
    suspend fun upVote(@Path("id") linkID: String)

    @POST("bucket/link/{id}/downvote/")
    suspend fun downVote(@Path("id") linkID: String)
}