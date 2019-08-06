package com.github.stulzm2.githubmodernarch.view

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.stulzm2.githubmodernarch.R
import com.github.stulzm2.githubmodernarch.model.Item
import com.github.stulzm2.githubmodernarch.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var blogAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getRepos()
        swiperefresh.setOnRefreshListener { getRepos() }
    }

    private fun getRepos() {
        swiperefresh.isRefreshing = false
        mainViewModel.allRepos.observe(this, Observer { repoList ->
            prepareRecyclerView(repoList)
        })
    }

    private fun prepareRecyclerView(repoList: List<Item>) {
        blogAdapter = RepoAdapter(repoList) { item: Item -> repoItemClicked(item) }
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            blogRecyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            blogRecyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        blogRecyclerView.itemAnimator = DefaultItemAnimator()
        blogRecyclerView.adapter = blogAdapter
    }

    private fun repoItemClicked(repo: Item) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.data = Uri.parse(repo.html_url)
        this.startActivity(intent)
    }
}
