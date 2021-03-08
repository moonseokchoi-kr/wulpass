package com.ccu.wulpass.ui.wallet

import android.app.Application
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ccu.wulpass.encryption.PemFactory
import com.ccu.wulpass.rest.data.Contract
import com.ccu.wulpass.rest.data.PreferenceManager
import com.ccu.wulpass.rest.data.ResultContract
import com.ccu.wulpass.rest.repo.DIDRepo
import java.util.logging.Handler

class WalletViewModel(application: Application):AndroidViewModel(application) {

    private val context = application.applicationContext
    private val repo = DIDRepo(context)
    private val contracts : ArrayList<Contract> = arrayListOf()
    private val walletAdapter = WalletAdapter(this)
    private val id = PreferenceManager.getString(context,"ID")
    fun getContract() {
        repo.getContract().subscribe{
            for(i in ArrayList(it.items)){
                if(i.consumerID =="wul:did:$id" ){
                    contracts.add(i)
                    android.os.Handler(Looper.getMainLooper()).post{
                        walletAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    fun getContext(pos:Int):String{
        return contracts[pos].context
    }

    fun getCreate(pos:Int):String{
        return contracts[pos].consumerID
    }

    fun getContract(pos:Int):String{
        return contracts[pos].contract
    }
    fun viewInit(recyclerView: RecyclerView){
        recyclerView.adapter = walletAdapter
        recyclerView.layoutManager = LinearLayoutManager(getApplication())
    }
    fun verifySignature(pos:Int){
        if(PemFactory.verifyContract(getContract(pos),"Org1")){
            val toast = Toast.makeText(context, "거래사실이 확인되었습니다.", Toast.LENGTH_LONG)
            toast.show()
        }

    }
    fun getContracts():ArrayList<Contract>{
        return contracts
    }

}