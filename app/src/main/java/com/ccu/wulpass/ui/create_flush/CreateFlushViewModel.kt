package com.ccu.wulpass.ui.create_flush

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.encryption.IDEncryption
import com.ccu.wulpass.rest.data.*
import com.ccu.wulpass.rest.repo.DIDRepo
import com.ccu.wulpass.rest.repo.UserRepo

class CreateFlushViewModel(application: Application):AndroidViewModel(application) {
    private val context = application.applicationContext
    private val did : DIDRepo = DIDRepo(context)
    private val id  = PreferenceManager.getString(application.applicationContext,"ID")
    private var createResult : MutableLiveData<Result> = MutableLiveData()
    fun createDID(){
        did.createDID(DIDFactory.createRequest(id!!,"Org1")).subscribe {
            createResult.value = it
            val toast = Toast.makeText(context, it.result, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}