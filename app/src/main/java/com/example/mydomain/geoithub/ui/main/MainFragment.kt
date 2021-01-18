package com.example.mydomain.geoithub.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mydomain.geoithub.GithubRepoAdapter
import com.example.mydomain.geoithub.GithubRepoEntry
import com.example.mydomain.geoithub.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var githubRepoEntries = mutableListOf<GithubRepoEntry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {

        viewManager = LinearLayoutManager(context)
        viewAdapter = GithubRepoAdapter(githubRepoEntries)

        val fragmentView = inflater.inflate(R.layout.main_fragment, container, false)

        fragmentView.findViewById<RecyclerView>(R.id.github_repos_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return fragmentView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(
                this,
                mainViewModelFactory {
                    MainViewModel(
                        "https://api.github.com/users/gsalinaslopez/repos",
                        context
                    )
                }
        ).get(MainViewModel::class.java)

        viewModel.githubRepoEntries.observe(viewLifecycleOwner, Observer { repoEntries ->
            Log.i("VOLLEY", "livedata updated, " + repoEntries!!.size)

            for (entry in repoEntries) {
                githubRepoEntries.add(entry)
            }
            viewAdapter.notifyDataSetChanged()
        })

    }

    protected inline fun <viewModel: ViewModel> mainViewModelFactory(crossinline func: () -> viewModel) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = func() as T
        }
}
