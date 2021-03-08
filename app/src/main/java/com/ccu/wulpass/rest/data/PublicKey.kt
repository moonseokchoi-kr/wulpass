package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class PublicKey(@SerializedName("id") val id: String, @SerializedName("type") val type: String, @SerializedName("publicKeybase64") val publicKey : String, @SerializedName("created") val created: Long, @SerializedName("revoked") val revoked: Long) {
}