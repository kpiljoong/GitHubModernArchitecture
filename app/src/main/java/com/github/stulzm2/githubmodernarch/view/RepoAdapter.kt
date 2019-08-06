package com.github.stulzm2.githubmodernarch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.stulzm2.githubmodernarch.R
import com.github.stulzm2.githubmodernarch.model.Item
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter(private val repoList: List<Item>?, private val clickListener: (Item) -> Unit) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repoList?.get(position)!!, clickListener)
    }

    override fun getItemCount(): Int = repoList!!.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindRepo(repo: Item, clickListener: (Item) -> Unit) {
            itemView.username.text = repo.owner.login.orEmpty()
            itemView.repoName.text = repo.name.orEmpty()
            itemView.repoDescription.text = repo.description.orEmpty()
            itemView.setOnClickListener { clickListener(repo) }

            Glide.with(itemView.context).load(repo.owner.avatar_url).into(itemView.avatar)
        }
    }
}