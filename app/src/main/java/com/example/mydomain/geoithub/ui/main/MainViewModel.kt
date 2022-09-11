package com.example.mydomain.geoithub.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mydomain.geoithub.GithubRepoEntry
import org.json.JSONArray
import org.json.JSONObject
import java.security.AccessControlContext

class MainViewModel(
    private val githubRepoFetchURL: String, var context: Context?
) : ViewModel() {

    val githubRepoEntries: MutableLiveData<MutableList<GithubRepoEntry>> by lazy {
        MutableLiveData<MutableList<GithubRepoEntry>>()
    }

    val volleyHTTPRequestQueue = Volley.newRequestQueue(context)

    init {
        Log.i("VOLLEY", "constructor called")

        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, githubRepoFetchURL, null,
            Response.Listener { response ->

                val repoEntryMutableList = mutableListOf<GithubRepoEntry>()
                for (i in 0 until response.length()) {
                    val repoEntryJSONObject = response.getJSONObject(i)
                    //Log.i("VOLLEY", "Response " + repoEntryJSONObject.getString("description"))

                    //repoEntryMutableList.add()
                    val githubRepoEntry = GithubRepoEntry(
                        repoEntryJSONObject.getString("name"),
                        repoEntryJSONObject.getString("description"),
                        repoEntryJSONObject.getString("language"),
                        repoEntryJSONObject.getString("updated_at")
                    )
                    repoEntryMutableList.add(githubRepoEntry)
                }
                githubRepoEntries.value = repoEntryMutableList

            },
            Response.ErrorListener { error ->
                Log.i("VOLLEY", "Error Response: %s".format(error.toString()))
            }
        )
        volleyHTTPRequestQueue.add(jsonObjectRequest)
    }

}
