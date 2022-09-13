package com.example.retrofitconnection.gson

import android.nfc.Tag
import android.util.Log
import com.example.retrofitconnection.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import com.example.retrofitconnection.utils.Constants.TAG
import com.example.retrofitconnection.utils.RESPONSE_STATE

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //사진 검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {

        //널처리 방법 중하나 Unwrapping //옵셔널로 선언하면 널을 받을 수 있지만 필수로 필요한 인자는 ? 옵셔널을 빼야하기 때문에 이런 후 처리 방법들을 사용
        val term = searchTerm.let {
            it
        }?: ""
        // val term = searchTerm? : ""  // 과 동일
        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                Log.d(TAG, "response success ${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            override fun onFailure(call: Call<JsonElement>, throwable: Throwable) {
                Log.d(TAG, "response failure ${throwable}")
                completion(RESPONSE_STATE.FAIL, throwable.toString())

            }

        })


    }
}