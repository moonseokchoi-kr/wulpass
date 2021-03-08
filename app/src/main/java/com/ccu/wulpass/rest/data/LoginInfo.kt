package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

data class LoginInfo(@SerializedName("username")val username: String, @SerializedName("orgName")val orgName: String){
}