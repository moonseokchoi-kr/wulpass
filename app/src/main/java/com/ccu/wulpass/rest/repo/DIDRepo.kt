package com.ccu.wulpass.rest.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.rest.connection.DIDAPI
import com.ccu.wulpass.rest.data.*

import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONObject

class DIDRepo(context:Context) {

    private val api = PreferenceManager.getString(context,"TOKEN")?.let { DIDAPI.create(context) }
    private val user = PreferenceManager.getString(context,"ID")
    private val org = PreferenceManager.getString(context, "ORG")
    private var result : MutableLiveData<Result> = MutableLiveData()


    fun createDID(did: DIDRequest): @NonNull Observable<Result> = api!!.createDID(did, org, user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
            Log.d("Error Occurr!", "Error : ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
    fun getDID(): @NonNull Observable<DID> = api!!.getDID(org, user, "wul:did:$user")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
            Log.d("Error Occurr!", "Error : ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
    fun createContract(sign:String, ctc:String) : Observable<Result> = api!!.createContract(DIDFactory.createContract(user!!,org!!,ctc,sign),org,user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
            Log.d("Error Occurr!", "Error : ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
    fun getContract(): @NonNull Observable<ResultContract> = api!!.getAllContract(org, user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError{
            Log.d("Error Occurr!", "Error : ${it.printStackTrace()}")
        }
        .doOnComplete {
            Log.d("Complete", "Complete POST Request")
        }
}
