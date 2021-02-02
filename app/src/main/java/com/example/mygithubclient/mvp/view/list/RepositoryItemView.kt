package com.example.mygithubclient.mvp.view.list

interface RepositoryItemView : IItemView {
    fun setName(name: String?)
    fun setForksCount(count: Int?)
}