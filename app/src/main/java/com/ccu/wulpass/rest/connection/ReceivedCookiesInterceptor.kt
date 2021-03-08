package com.ccu.wulpass.rest.connection

import android.content.Context
import com.ccu.wulpass.rest.data.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor(val context: Context) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if(response.headers("Set-Cookie").isNotEmpty()){
            PreferenceManager.setString(context,"Cookie",response.header("Set-Cookie"))
        }
        return response
    }
}