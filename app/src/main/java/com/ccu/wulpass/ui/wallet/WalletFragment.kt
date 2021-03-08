package com.ccu.wulpass.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ccu.wulpass.R
import com.ccu.wulpass.databinding.WalletFragmentBinding
import com.ccu.wulpass.ui.create_flush.CreateFlushFragment

class WalletFragment : Fragment() {

    companion object{
        fun newInstance()= CreateFlushFragment()
    }
    private lateinit var viewModel : WalletViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: WalletFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.wallet_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        viewModel.viewInit(binding.fragmentRecycleView)
        viewModel.getContract()
        binding.walletViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}