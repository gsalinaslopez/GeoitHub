package com.example.mydomain.geoithub

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mydomain.geoithub.ui.main.MainViewModel

class GithubRepoAdapter(private val githubRepoEntries: List<GithubRepoEntry>) :
        RecyclerView.Adapter<GithubRepoAdapter.GithubRepoViewHolder>() {

    class GithubRepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.card_title)
        val content = view.findViewById<TextView>(R.id.card_content)
    }

    override fun getItemCount(): Int {
        Log.i("Adapter", "list got updated, size is: " + githubRepoEntries.size)
        return githubRepoEntries.size
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {

        holder.title.text = githubRepoEntries[position].repoTitle
        holder.content.text = githubRepoEntries[position].repoShortDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        val repoView = LayoutInflater.from(parent.context).
                inflate(R.layout.repo_card, parent, false)
        return GithubRepoViewHolder(repoView)
    }
}