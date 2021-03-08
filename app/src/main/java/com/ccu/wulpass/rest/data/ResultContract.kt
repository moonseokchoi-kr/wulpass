package com.ccu.wulpass.rest.data

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class ResultContract(@SerializedName("items") val items: List<Contract>) {
}