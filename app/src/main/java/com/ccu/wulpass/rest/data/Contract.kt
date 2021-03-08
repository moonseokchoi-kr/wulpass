package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName

data class Contract (@SerializedName("context") val context : String,@SerializedName("id") val id : String, @SerializedName("sellerid") val sellerID : String, @SerializedName("consumerid") val consumerID:String, @SerializedName("created") val created: String, @SerializedName("contract") val contract : String, @SerializedName("signature") val signature : String){
}

