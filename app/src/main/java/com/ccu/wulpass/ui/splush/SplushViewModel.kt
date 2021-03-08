package com.ccu.wulpass.ui.splush

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.AccessResult
import com.ccu.wulpass.rest.repo.UserInfo
import com.ccu.wulpass.rest.repo.UserRepo

class SplushViewModel(application: Application):AndroidViewModel(application) {
    private val context = application.applicationContext
    private var loginResult: MutableLiveData<AccessResult> = MutableLiveData()
    private val repo = UserRepo(context)
    fun loginUser(){
        if(UserInfo.getFile(context).exists()){
            val fileLoginInfo = UserInfo.readUserFile(context)
            repo.loginUser(fileLoginInfo!!).subscribe { it ->
                loginResult.value = it
                val toast = Toast.makeText(context,it.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}