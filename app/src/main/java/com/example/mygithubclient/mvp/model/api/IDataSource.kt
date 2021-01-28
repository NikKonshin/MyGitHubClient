package com.example.mygithubclient.mvp.model.api

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("/users/{login}")
    fun getUserData(@Path("login") login: String): Single<GithubUser>

    @GET("users/{login}/repos")
    fun getRepositories(@Path("login") login: String): Single<List<GithubRepository>>
}