package com.example.mygithubclient.mvp.di.modules

import com.example.mygithubclient.mvp.model.api.IDataSource
import com.example.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.example.mygithubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.mygithubclient.mvp.model.entity.room.Database
import com.example.mygithubclient.mvp.model.network.INetworkStatus
import com.example.mygithubclient.mvp.model.repo.IGithubUserRepositories
import com.example.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.example.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoryRepo
import com.example.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.mygithubclient.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache,
    ): IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache,
    ): IGithubUserRepositories =
        RetrofitGithubRepositoryRepo(api, networkStatus, cache)
}