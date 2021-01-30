package com.example.mygithubclient.mvp.model.cache

import com.example.mygithubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun insert(users: List<GithubUser>): Completable

    fun insert(user: GithubUser): Completable

    fun delete(user: GithubUser): Completable

    fun getAll(): Single<List<GithubUser>>

    fun getUser(user: GithubUser): Single<GithubUser>

}
