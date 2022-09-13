package com.example.retrofitconnection.gson

import android.util.Log
import com.example.retrofitconnection.utils.API
import com.example.retrofitconnection.utils.Constants.TAG
import com.example.retrofitconnection.utils.isJsonArray
import com.example.retrofitconnection.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object RetrofitClient {

    fun getClient(baseUrl: String): Retrofit? {
        Log.d(TAG, "RetrofitClient getClient() called")

        //로깅 인터셉터 추가

        //okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        //로그를 찍기 위해 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(TAG, "RetrofitClient - log() called / message: $message")
            when {
                message.isJsonObject() ->
                    Log.d(TAG, JSONObject(message).toString(4)) //4칸 띄기, 들여쓰기
                message.isJsonArray() ->
                    Log.d(TAG, JSONObject(message).toString(4))
                else -> {
                    try {
                        Log.d(TAG, JSONObject(message).toString(4))
                    } catch (e: Exception) {
                        Log.d(TAG, message)

                    }
                }
            }

        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)

        //기본 파라메터 추가
        val baseParameterInterceptor: Interceptor = (object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")
                val originalRequest = chain.request()

                // ? client_id=adfgadsf~
                //쿼리 파라메터 추가하기
                val addedUrl =
                    originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID)
                        .build()
                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }

        })

        client.addInterceptor(baseParameterInterceptor)

        //커넥션 타임 아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            //위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
            .client(client.build())
            .build()
    }


}