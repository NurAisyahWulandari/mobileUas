package com.example.recyclerview.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.adapter.ListAdapter
import com.example.recyclerview.data.Music

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val list= ArrayList<Music>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.rv_video)
        recyclerView.setHasFixedSize(true)
        list.addAll(getList())
        showRecyclerList()
        setupView()
    }

    private fun getList():ArrayList<Music>{
        val gambar=resources.obtainTypedArray(R.array.data_gambar)
        val dataName=resources.getStringArray(R.array.judul_music)
        val dataDesripsi=resources.getStringArray(R.array.data_dekripsi)
        val musicId=resources.obtainTypedArray(R.array.music_id)
        val listvideo=ArrayList<Music>()
        for (i in dataName.indices){
            val music= Music(gambar.getResourceId(i,-1),dataName[i],dataDesripsi[i],musicId.getResourceId(i,0))
            listvideo.add(music)
        }
        return listvideo
    }
    private fun showRecyclerList(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        val listadapter= ListAdapter(list)
        recyclerView.adapter=listadapter
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

}