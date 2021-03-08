package com.ccu.wulpass.ui.contract

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.rest.data.DIDFactory
import com.ccu.wulpass.rest.data.Result
import com.ccu.wulpass.rest.repo.DIDRepo

class ContractViewModel (application: Application):AndroidViewModel(application){
    private val context = application.applicationContext
    private val repo = DIDRepo(context)
    private val result : MutableLiveData<Result> = MutableLiveData()

    fun createContract(sign:String, ctc:String){
        repo.createContract(sign, ctc).subscribe{
            val toast = Toast.makeText(context,it.result,Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}