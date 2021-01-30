package com.example.mygithubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubclient.ApiHolder
import com.example.mygithubclient.App
import com.example.mygithubclient.R
import com.example.mygithubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.example.mygithubclient.mvp.model.entity.room.Database
import com.example.mygithubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.mygithubclient.mvp.presenter.UsersPresenter
import com.example.mygithubclient.mvp.view.UsersView
import com.example.mygithubclient.ui.BackButtonListener
import com.example.mygithubclient.ui.adapter.UsersRVAdapter
import com.example.mygithubclient.ui.image.GlideImageLoader
import com.example.mygithubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(
                ApiHolder().api, AndroidNetworkStatus(App.instance),
                RoomGithubUsersCache(Database.getInstance())
            ),
            App.instance.router
        )
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        rv_users.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}