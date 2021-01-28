package com.example.mygithubclient.mvp.presenter

import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.repo.IGithubUserRepositories
import com.example.mygithubclient.mvp.presenter.list.IRepositoryListPresenter
import com.example.mygithubclient.mvp.view.UserView
import com.example.mygithubclient.mvp.view.list.RepositoryItemView
import com.example.mygithubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val mainThreadScheduler: Scheduler,
    private val userRepositories: IGithubUserRepositories,
    private val user: GithubUser,
    private val router: Router
) :
    MvpPresenter<UserView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name.let { view.setName(it) }
            repository.forks.let { view.setForksCount(it) }
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoryListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.RepositoryScreen(repositoryListPresenter.repositories[itemView.pos]))
        }
    }

    private fun loadData() {
        userRepositories.getRepositories(user.login)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoryListPresenter.repositories.clear()
                repositoryListPresenter.repositories.addAll(repositories)
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