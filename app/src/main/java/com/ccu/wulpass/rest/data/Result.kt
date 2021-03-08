package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName

data class Result(@SerializedName("result") var result: String, @SerializedName("error") var error: String, @SerializedName("errorData") val errorData: String) {
}