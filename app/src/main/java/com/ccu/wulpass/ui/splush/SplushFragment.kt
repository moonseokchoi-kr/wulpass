package com.ccu.wulpass.ui.splush

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ccu.wulpass.R
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.rest.repo.UserInfo
import com.ccu.wulpass.ui.id.IDFragment
import com.ccu.wulpass.ui.login.LoginFragment
import com.ccu.wulpass.ui.main.NavActivity


class SplushFragment : Fragment() {
    companion object{
        fun newInstance() = SplushFragment()
    }
    private lateinit var sendData : SendData
    private lateinit var viewModel: SplushViewModel
    private lateinit var btn : Button
    private lateinit var btns : Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.flush_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SplushViewModel::class.java)
        sendData = activity as SendData
        btn = root.findViewById(R.id.btn_next)
        btns = root.findViewById(R.id.btn_start)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = context?.let { PreferenceManager.getString(it,"ID") }
        if(id.isNullOrBlank()){
            btns.visibility=View.VISIBLE
            btn.visibility=View.GONE
            btns.setOnClickListener {
                viewModel.loginUser()
                btn.visibility=View.VISIBLE
                btns.visibility=View.GONE
            }
            btns.setOnClickListener{
                sendData.nextActivity("","",NavActivity::class.java)
            }
        }else{
            btn.setOnClickListener{
                sendData.passData("","",LoginFragment())
            }

        }

    }
}