package com.example.mygithubclient.mvp.presenter

import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.repo.IGithubUsersRepo
import com.example.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.example.mygithubclient.mvp.view.UsersView
import com.example.mygithubclient.mvp.view.list.UserItemView
import com.example.mygithubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,
    private val usersRepo: IGithubUsersRepo,
    private val router: Router
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            usersRepo
                .getUserData(usersListPresenter.users[itemView.pos])
                .observeOn(mainThreadScheduler)
                .subscribe({
                    router.navigateTo(Screens.UserScreen(it))
                },{
                    println("Error: ${it.message}")
                })
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}