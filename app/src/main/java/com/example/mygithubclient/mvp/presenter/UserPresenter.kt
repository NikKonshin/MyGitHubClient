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
import javax.inject.Inject

class UserPresenter(
    private val user: GithubUser,
) :
    MvpPresenter<UserView>() {
    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var userRepositories: IGithubUserRepositories

    @Inject
    lateinit var router: Router

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
        userRepositories.getRepositories(user)
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