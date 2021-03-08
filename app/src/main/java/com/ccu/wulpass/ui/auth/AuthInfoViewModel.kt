package com.ccu.wulpass.ui.auth

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ccu.wulpass.encryption.IDEncryption
import com.ccu.wulpass.rest.data.*
import com.ccu.wulpass.rest.repo.DIDRepo
import com.ccu.wulpass.rest.repo.UserInfo
import com.ccu.wulpass.rest.repo.UserRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import java.util.logging.Handler
import kotlin.math.log

class AuthInfoViewModel(application: Application):AndroidViewModel(application) {

    private var registerResult: MutableLiveData<AccessResult> = MutableLiveData()

    private val repo : UserRepo = UserRepo(application.applicationContext)
    private val context = application.applicationContext
    private val id : String = IDEncryption().getUUID()
    private var loginInfo : LoginInfo = LoginInfo(id,"Org1")
    fun register(){
        PreferenceManager.setString(context,"ID", loginInfo.username)
        repo.registerUser(loginInfo).subscribe { it ->
            registerResult.value = it
            val toast = Toast.makeText(context,it.message, Toast.LENGTH_SHORT)
            toast.show()
        }

    }
    fun getLoginInfo():LoginInfo{
        return loginInfo
    }

}