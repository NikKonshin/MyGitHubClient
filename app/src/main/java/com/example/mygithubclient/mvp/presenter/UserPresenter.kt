package com.example.mygithubclient.mvp.presenter

import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.view.UserView
import com.example.mygithubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setText(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}