package com.ccu.wulpass.rest.connection


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.ccu.wulpass.rest.data.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor(val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val preference = PreferenceManager.getString(context, "Cookie")
        Log.d("Cookie", preference)
        if (preference != null) {
            builder.addHeader("Cookie", preference)
        }

        return chain.proceed((builder.build()))
    }
}

