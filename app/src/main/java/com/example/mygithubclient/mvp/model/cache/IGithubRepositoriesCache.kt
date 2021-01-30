package com.example.mygithubclient.mvp.model.cache

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.entity.room.RoomGithubRepository
import com.example.mygithubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun insert(user: GithubUser, repository: List<GithubRepository>): Completable

    fun update(user: GithubUser, repository: List<GithubRepository>): Completable

    fun delete(user: GithubUser, repository: List<GithubRepository>): Completable

    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>

}