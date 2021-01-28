package com.example.mygithubclient.mvp.presenter

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val userRepo: GithubRepository, private val router: Router) :
    MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun getForksCount() = userRepo.forks

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}