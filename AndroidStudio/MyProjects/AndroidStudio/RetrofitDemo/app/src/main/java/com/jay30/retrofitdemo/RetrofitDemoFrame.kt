package com.jay30.retrofitdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Retrofit
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.database.core.Repo

//@SuppressLint("RestrictedApi")
@Composable
fun SimpleService(){
    val TAG = "TAG_RetrofitDemoFrame"

    var repoToString by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service = retrofit.create(GitHubService::class.java)
        Log.e(TAG,"successfully create the service `GitHubService`.")
        val repo = service.listRepos("octocat")
        Log.e(TAG,"successfully handle the service of list repo `octocat`. repo:${repo}")
        repoToString = repo.toString()
        Log.e(TAG,"successfully fetch the info of repo `octocat`. repoToString:${repoToString}")
    }

    LazyColumn(
        modifier = Modifier
            .size(400.dp,400.dp),
    ){
        item {
            Text("${repoToString}")
        }
    }

}