package com.ccu.wulpass.ui.create_flush

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.ui.auth.AuthInfoViewModel
import com.ccu.wulpass.ui.flush.FlushFragment
import com.ccu.wulpass.ui.flush.FlushViewModel
import com.ccu.wulpass.ui.id.IDFragment
import com.ccu.wulpass.ui.main.NavActivity
import kotlinx.android.synthetic.main.create_flush_fragment.*
import kotlinx.coroutines.awaitAll

class CreateFlushFragment : Fragment(){
    companion object{
        fun newInstance()=CreateFlushFragment()
    }
    private lateinit var viewModel : CreateFlushViewModel
    private lateinit var btnLiners: LinearLayout
    private lateinit var btn : Button
    private lateinit var btns : Button
    private lateinit var sendData : SendData
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.create_flush_fragment, container, false)
        viewModel = ViewModelProvider(this).get(CreateFlushViewModel::class.java)
        btn = root.findViewById(R.id.btn_create)
        btns = root.findViewById(R.id.btn_complete)
        sendData = activity as SendData
        btnLiners= root.findViewById(R.id.linearLayout2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            viewModel.createDID()
            btnLiners.visibility=View.GONE
            btns.visibility=View.VISIBLE

        }
        btns.setOnClickListener {
            btns.visibility=View.GONE
            btnLiners.visibility= View.VISIBLE
            sendData.nextActivity("","",NavActivity::class.java)
        }
    }
}