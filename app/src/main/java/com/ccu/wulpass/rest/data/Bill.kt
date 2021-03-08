package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName

data class Bill(@SerializedName("context") val context : String, @SerializedName("seller") val seller : String, @SerializedName("consumer") val consumer: String, @SerializedName("goods") val goods :String, @SerializedName("price") val price:String ){

}