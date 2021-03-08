package com.ccu.wulpass.rest.repo

import android.content.Context
import com.ccu.wulpass.rest.data.LoginInfo
import com.google.gson.Gson
import java.io.File
import java.io.InputStream
import java.io.OutputStream

object UserInfo {

    private fun getContext(context: Context):Context{
        return context
    }


    fun writeUserFile(loginInfo: LoginInfo,context: Context){
        val gson = Gson()
        val str = gson.toJson(loginInfo)
        val context = getContext(context)
        val openOutputStream = context.openFileOutput(getFile(context).name,Context.MODE_PRIVATE)

        openOutputStream.use {
            it.write(str.toByteArray())
        }
        openOutputStream.close()
    }

    fun readUserFile(context: Context): LoginInfo?{
        val gson = Gson()
        val context = getContext(context)
        val inputStream= context.openFileInput(getFile(context).name)

        val bytes = byteArrayOf()
        if(!getFile(context).exists())
            return null
        inputStream.use {
            it.read(bytes)
        }
        inputStream.close()
        return gson.fromJson(String(bytes),LoginInfo::class.java)

    }

    fun getFile(context:Context): File {

        return File(context.filesDir, "userInfo.json")
    }

}