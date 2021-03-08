package com.ccu.wulpass.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.ui.auth.AuthInfoFragment
import com.ccu.wulpass.ui.auth.AuthInfoViewModel
import com.ccu.wulpass.ui.create_flush.CreateFlushFragment
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    companion object{
        fun newInstance() = LoginFragment()
    }
    private lateinit var viewModel : LoginViewModel
    private lateinit var btnLogin : Button
    private lateinit var sendData : SendData
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.login_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        btnLogin = root.findViewById(R.id.btn_login)
        sendData = activity as SendData
        btnLogin.setOnClickListener{
            sendData.passData("","",AuthInfoFragment())
        }
        return root
    }


}