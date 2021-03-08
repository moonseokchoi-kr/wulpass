package com.ccu.wulpass.ui.contract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.encryption.PemFactory
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.rest.data.Bill
import com.ccu.wulpass.ui.create_flush.CreateFlushFragment
import com.google.gson.Gson

class ContractFragment : Fragment() {
    companion object{
        fun newInstance()= CreateFlushFragment()
    }
    private lateinit var textViewContext : TextView
    private lateinit var textViewSeller : TextView
    private lateinit var textViewConsumer : TextView
    private lateinit var editTextGoods : EditText
    private lateinit var editTextPrice : EditText
    private lateinit var btn: Button
    private lateinit var sendData: SendData
    private lateinit var viewModel: ContractViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =inflater.inflate(R.layout.contract_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(ContractViewModel::class.java)
        textViewContext = root.findViewById(R.id.textview_context)
        textViewSeller = root.findViewById(R.id.textview_seller)
        textViewConsumer=root.findViewById(R.id.textview_consumer)
        editTextGoods = root.findViewById(R.id.editText_goods)
        editTextPrice = root.findViewById(R.id.editText_prices)
        btn = root.findViewById(R.id.btn_sign)
        sendData = activity as SendData
        return root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener{
            if(editTextGoods.text.isNotBlank() && editTextPrice.text.isNotBlank()){
                val bill = Bill(textViewContext.text as String, textViewSeller.text as String, textViewConsumer.text as String, editTextGoods.text.toString(), editTextPrice.text.toString())
                val gson = Gson()
                val str: String = gson.toJson(bill)
                PemFactory.generateEncryptKey()
                val sign = PemFactory.signContract(str,"Org1")

                viewModel.createContract(sign,str)
            }else{
                val toast = Toast.makeText(context, "모든 빈칸을 채워주세요", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }
}