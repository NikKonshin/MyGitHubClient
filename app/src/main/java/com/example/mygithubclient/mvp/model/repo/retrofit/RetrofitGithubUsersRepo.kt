package com.example.mygithubclient.mvp.model.repo.retrofit

import com.example.mygithubclient.mvp.model.api.IDataSource
import com.example.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.network.INetworkStatus
import com.example.mygithubclient.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userCache: IGithubUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                userCache.insert(users).toSingleDefault(users)
            }
        } else {
            userCache.getAll()
        }
    }.subscribeOn(Schedulers.io())

    override fun getUserData(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserData(user.login).flatMap { user ->
                    userCache.insert(user).toSingleDefault(user)
                }
            } else {
                userCache.getUser(user)
            }
        }.subscribeOn(Schedulers.io())
}