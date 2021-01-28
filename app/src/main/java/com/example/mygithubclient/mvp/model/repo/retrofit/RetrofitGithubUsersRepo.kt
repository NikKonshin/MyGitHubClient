package com.example.mygithubclient.mvp.model.repo.retrofit

import com.example.mygithubclient.mvp.model.api.IDataSource
import com.example.mygithubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserData(login: String) = api.getUserData(login).subscribeOn(Schedulers.io())
}