package com.example.mygithubclient.mvp.model.repo.retrofit

import com.example.mygithubclient.mvp.model.api.IDataSource
import com.example.mygithubclient.mvp.model.repo.IGithubUserRepositories
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoryRepo(private val api: IDataSource): IGithubUserRepositories {
    override fun getRepositories(login: String) = api.getRepositories(login).subscribeOn(Schedulers.io())
}