package com.example.retrofitconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitconnection.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val emgMedAdapter by lazy {
        EmgMedAdatper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = emgMedAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

//        binding.btnGet.setOnClickListener{
            retrofitWork()
//        }
    }

    private fun retrofitWork() {
        val service = RetrofitApi.emgMedService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getDataCoroutine(getString(R.string.api_key), "json")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val result = response.body()?.cORSVST?.row
                    result?.let {
                        emgMedAdapter.submitList(it)
                    }
                } else {
                    Log.d("TAG", response.code().toString())
                }
            }
        }
    }
}