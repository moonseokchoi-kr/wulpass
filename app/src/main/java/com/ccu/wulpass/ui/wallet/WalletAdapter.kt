package com.ccu.wulpass.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ccu.wulpass.databinding.WalletCardBinding

class WalletAdapter(viewModel: WalletViewModel): RecyclerView.Adapter<WalletAdapter.ViewHolder>() {
    val viewModel = viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: WalletCardBinding = WalletCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemCount(): Int {
        return viewModel.getContracts().size
    }

    inner class ViewHolder(binding: WalletCardBinding) : RecyclerView.ViewHolder(binding.root){
        val binding = binding
        fun bind(viewModel: WalletViewModel, pos: Int){
            binding.pos = pos
            binding.walletViewModel = viewModel
            binding.executePendingBindings()
        }
    }
}