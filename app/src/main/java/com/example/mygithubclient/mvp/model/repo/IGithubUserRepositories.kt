package com.example.mygithubclient.mvp.model.repo

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}