package com.example.retrofitconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitconnection.databinding.ActivityMainBinding
import com.example.retrofitconnection.moshi.EmgMedAdatper
import com.example.retrofitconnection.moshi.RetrofitApi
import com.example.retrofitconnection.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var main: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.pagerMain.adapter = ViewPagerAdapter( supportFragmentManager, this.lifecycle)

        TabLayoutMediator(main.tabMain, main.pagerMain) { tab, position ->
            tab.text = Constants.TABS[position]
        }.attach()


        //

    }


}