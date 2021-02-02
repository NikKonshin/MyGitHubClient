package com.example.mygithubclient.mvp.presenter

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryPresenter(
    private val userRepo: GithubRepository,
) :
    MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

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