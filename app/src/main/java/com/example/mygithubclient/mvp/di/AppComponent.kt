package com.example.mygithubclient.mvp.di

import com.example.mygithubclient.mvp.di.modules.*
import com.example.mygithubclient.mvp.presenter.MainPresenter
import com.example.mygithubclient.mvp.presenter.RepositoryPresenter
import com.example.mygithubclient.mvp.presenter.UserPresenter
import com.example.mygithubclient.mvp.presenter.UsersPresenter
import com.example.mygithubclient.ui.MainActivity
import com.example.mygithubclient.ui.adapter.UsersRVAdapter

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    DatabaseModule::class,
    CiceroneModule::class,
    RepoModule::class,
    ImageLoaderModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repository: RepositoryPresenter)
    fun inject(adapter: UsersRVAdapter)
}