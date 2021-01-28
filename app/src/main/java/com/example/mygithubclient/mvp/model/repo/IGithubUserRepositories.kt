package com.example.mygithubclient.mvp.model.repo

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getRepositories(login: String): Single<List<GithubRepository>>
}