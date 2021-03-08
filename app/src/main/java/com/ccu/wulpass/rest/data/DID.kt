package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class DID(@SerializedName("context")val context : String, @SerializedName("id") val id: String,@SerializedName("created") val created: String, @SerializedName("updated") val updated: String, @SerializedName("publicKey") val publicKey : String, @SerializedName("authenticaiton") val auth: String, @SerializedName("service") val service: List<Service>) {

}