package com.ccu.wulpass.rest.connection

import android.content.Context
import android.content.SharedPreferences
import com.ccu.wulpass.rest.data.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import retrofit2.http.Body as Body

interface DIDAPI {
    @PUT("/contract/{orgname}/{username}")
    fun createContract(
        @Body contract: Contract,
        @Path("orgname") orgname: String?,
        @Path("username") userName: String?
    ):Observable<Result>
    @GET("/contract/{orgname}/{username}")
    fun getAllContract(
        @Path("orgname") orgname: String?,
        @Path("username") userName: String?
    ):Observable<ResultContract>
    @PUT("/did/{orgname}/{username}")
    fun createDID(
        @Body didRequest: DIDRequest,
        @Path("orgname") orgname: String?,
        @Path("username") userName: String?
    ):Observable<Result>
    @GET("/did/{orgname}/{username}/{id}")
    fun getDID(
        @Path("orgname") orgname: String?,
        @Path("username") userName: String?,
        @Path("id") id: String
    ):Observable<DID>
    companion object{
        private const val BASE_URL_DID = "http://wuldid.ddns.net"

        fun create(context:Context): DIDAPI {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val receivedCookiesInterceptor = ReceivedCookiesInterceptor(context)
            val addCookiesInterceptor = AddCookiesInterceptor(context)
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
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL_DID)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DIDAPI::class.java)
        }
    }
}