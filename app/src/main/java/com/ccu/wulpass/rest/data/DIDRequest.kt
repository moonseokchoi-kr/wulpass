package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName

data class DIDRequest(@SerializedName("id") val id : String, @SerializedName("created") val created: String, @SerializedName("publicKey") val publicKey : String, @SerializedName("authenticaiton") val auth: String) {
}