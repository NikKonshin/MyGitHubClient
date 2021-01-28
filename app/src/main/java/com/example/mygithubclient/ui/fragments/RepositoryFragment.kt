package com.example.mygithubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygithubclient.App
import com.example.mygithubclient.R
import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.presenter.RepositoryPresenter
import com.example.mygithubclient.mvp.view.RepositoryView
import com.example.mygithubclient.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment(private val userRepo: GithubRepository) : MvpAppCompatFragment(),
    RepositoryView, BackButtonListener {

    companion object {
        fun newInstance(userRepo: GithubRepository) = RepositoryFragment(userRepo)
    }

    private val presenter by moxyPresenter {
        RepositoryPresenter(userRepo, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_repository, container, false)

    override fun init() {
        repo_fragment_forks_count.text = presenter.getForksCount().toString()
    }

    override fun backPressed() = presenter.backPressed()


}