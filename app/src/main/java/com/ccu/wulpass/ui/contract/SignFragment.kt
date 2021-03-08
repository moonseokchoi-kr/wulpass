package com.ccu.wulpass.ui.contract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.encryption.PemFactory
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.ui.main.NavActivity

class SignFragment : Fragment() {
    companion object{
        fun newInstance() = SignFragment()
    }
    private lateinit var viewModel : ContractViewModel
    private lateinit var btnLogin : Button
    private lateinit var btnComplete:Button
    private lateinit var sendData : SendData
    private lateinit var data : String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.sign_activity, container, false)
        viewModel = ViewModelProviders.of(this).get(ContractViewModel::class.java)
        btnComplete = root.findViewById(R.id.btn_complete)
        btnLogin = root.findViewById(R.id.btn_login)
        sendData = activity as SendData
        data = PemFactory.encryptContract(arguments?.getString("bill")!!)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sign = PemFactory.signContract(data,"Org1")
        btnLogin.setOnClickListener {
            viewModel.createContract(sign,data)
            btnLogin.visibility=View.GONE
            btnComplete.visibility=View.VISIBLE
        }
        btnComplete.setOnClickListener{
            btnLogin.visibility=View.VISIBLE
            btnComplete.visibility=View.GONE
            sendData.nextActivity("","", NavActivity::class.java)
        }
    }
}