package com.example.mygithubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubclient.ApiHolder
import com.example.mygithubclient.App
import com.example.mygithubclient.R
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoryRepo
import com.example.mygithubclient.mvp.presenter.UserPresenter
import com.example.mygithubclient.mvp.view.UserView
import com.example.mygithubclient.ui.BackButtonListener
import com.example.mygithubclient.ui.adapter.RepositoriesUserRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val user: GithubUser) : MvpAppCompatFragment(), UserView,
    BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment(user)
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoryRepo(ApiHolder().api),
            user,
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_user, null)

    private var adapter: RepositoriesUserRVAdapter? = null

    override fun init() {
        rv_repo.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesUserRVAdapter(presenter.repositoryListPresenter)
        rv_repo.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}








