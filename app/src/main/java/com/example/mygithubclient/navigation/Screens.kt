package com.example.mygithubclient.navigation

import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.ui.fragments.UserFragment
import com.example.mygithubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}