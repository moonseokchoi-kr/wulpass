package com.ccu.wulpass.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.ui.create_flush.CreateFlushFragment
import com.ccu.wulpass.ui.create_flush.CreateFlushViewModel

class AuthFragment : Fragment() {
    companion object {
        fun newInstance() = AuthFragment()
    }
    private lateinit var sendData : SendData
    private lateinit var viewModel: AuthInfoViewModel
    private lateinit var btn : Button
    private lateinit var btns : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.auth_info_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(AuthInfoViewModel::class.java)
        btn = root.findViewById(R.id.btn_next)
        btns = root.findViewById(R.id.btn_start)
        sendData = activity as SendData

        return root
    }

}