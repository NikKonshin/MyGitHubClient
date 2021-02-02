package com.example.mygithubclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithubclient.R
import com.example.mygithubclient.mvp.presenter.list.IRepositoryListPresenter
import com.example.mygithubclient.mvp.view.list.RepositoryItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class RepositoriesUserRVAdapter(private val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesUserRVAdapter.ViewHolder>() {
    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, RepositoryItemView {
        override fun setName(name: String?) = with(containerView) {
            tv_name.text = name
        }

        override fun setForksCount(count: Int?) = with(containerView) {
            tv_forksCount.text = count.toString()
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}