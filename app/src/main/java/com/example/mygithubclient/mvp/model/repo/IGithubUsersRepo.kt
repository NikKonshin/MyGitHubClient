package com.example.mygithubclient.mvp.model.repo

import com.example.mygithubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserData(user: GithubUser): Single<GithubUser>
}