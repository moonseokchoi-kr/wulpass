package com.ccu.wulpass.rest.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.ccu.wulpass.rest.connection.LoginAPI
import com.ccu.wulpass.rest.data.AccessResult
import com.ccu.wulpass.rest.data.LoginInfo
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException


class UserRepo(context: Context) {

    private val api = LoginAPI.create(context)
    private var result :MutableLiveData<String> = MutableLiveData()


   fun registerUser(loginInfo:LoginInfo): @NonNull Observable<AccessResult> = api.registeredUser(loginInfo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
           Log.d("Error Occurr!", "Error : ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
    fun loginUser(loginInfo:LoginInfo): @NonNull Observable<AccessResult> = api.loginUser(loginInfo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
            Log.d("Error","Error Occur ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
    private fun handleError(throwable: Throwable) : Int{
        if(throwable is HttpException){
            val httpException = throwable as HttpException
            return httpException.code()
        }
        return 200
    }

}