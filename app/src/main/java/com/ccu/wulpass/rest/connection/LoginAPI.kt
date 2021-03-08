package com.ccu.wulpass.rest.connection

import android.content.Context
import com.ccu.wulpass.rest.data.AccessResult
import com.ccu.wulpass.rest.data.LoginInfo
import com.ccu.wulpass.rest.data.User
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/users")
    fun registeredUser(
        @Body loginInfo: LoginInfo
    ): Observable<AccessResult>
    @POST("/users/login")
    fun loginUser(
        @Body loginInfo: LoginInfo
    ): Observable<AccessResult>

    companion object{
        private const val BASE_URL_DID = "http://wuldid.ddns.net"
        fun create(context: Context):LoginAPI{
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val receivedCookiesInterceptor = ReceivedCookiesInterceptor(context)
            val addCookiesInterceptor = AddCookiesInterceptor(context)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val headerInterceptor = Interceptor{

                val request = it.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(receivedCookiesInterceptor)
                .addInterceptor(addCookiesInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL_DID)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(LoginAPI::class.java)
        }

    }

}