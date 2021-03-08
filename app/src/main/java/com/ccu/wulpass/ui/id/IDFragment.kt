package com.ccu.wulpass.ui.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.databinding.IdFragmentBinding
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.ui.auth.AuthInfoFragment
import com.ccu.wulpass.ui.create_flush.CreateFlushFragment
import com.ccu.wulpass.ui.login.LoginViewModel
import org.w3c.dom.Text
import java.text.SimpleDateFormat

class IDFragment: Fragment() {
    companion object{
        fun newInstance()= CreateFlushFragment()
    }
    private lateinit var viewModel : IDViewModel
    private lateinit var textID : TextView
    private lateinit var textCreate : TextView
    private lateinit var textPublickey : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.id_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(IDViewModel::class.java)
        textID = root.findViewById(R.id.text_id)
        textCreate= root.findViewById(R.id.text_created)
        textPublickey = root.findViewById(R.id.text_public)
        viewModel.getDID()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getResult().observe(viewLifecycleOwner, Observer {
            val time =it.created.toLong()
            val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            val date = sdf.format(time)
            textID.text = "ID: "+it.id
            textCreate.text ="Create: "+ date.toString()
            textPublickey.text="PublicKey: "+ it.publicKey
        })

    }

}