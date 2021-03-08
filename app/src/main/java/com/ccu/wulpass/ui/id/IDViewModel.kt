package com.ccu.wulpass.ui.id

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.CheckResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.rest.data.DID
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.rest.repo.DIDRepo
import java.text.SimpleDateFormat

class IDViewModel (application: Application):AndroidViewModel(application){
    private val context = application.applicationContext
    private val repo = DIDRepo(context)
    private val resultLiveData = MutableLiveData<DID>()
    fun getDID(){
        repo.getDID().subscribe{
            resultLiveData.value = it
        }
    }

    fun getResult():MutableLiveData<DID>{
        return resultLiveData
    }
}