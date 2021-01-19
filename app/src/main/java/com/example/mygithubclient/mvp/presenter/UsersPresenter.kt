package com.example.mygithubclient.mvp.presenter

import android.util.Log
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.entity.GithubUsersRepo
import com.example.mygithubclient.mvp.presenter.list.IUserListPresenter
import com.example.mygithubclient.mvp.view.UsersView
import com.example.mygithubclient.mvp.view.list.UserItemView
import com.example.mygithubclient.navigation.Screens
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

private const val TAG = "UsersPresenter"
class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    private var disposable: Disposable? = null

    private val usersObserver = object : Observer<GithubUser> {
        override fun onSubscribe(d: Disposable?) {
            disposable = d
        }

        override fun onNext(t: GithubUser?) {
            if (t != null) {
                usersListPresenter.users.add(t)
                Log.v(TAG, t.login)
            }
        }

        override fun onError(e: Throwable?) {
            if (e != null) {
                Log.v(TAG, e.message.toString())
            }
        }

        override fun onComplete() {
            Log.v(TAG, "onComplete()")
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(usersListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        usersRepo.getUsers().subscribe(usersObserver)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}