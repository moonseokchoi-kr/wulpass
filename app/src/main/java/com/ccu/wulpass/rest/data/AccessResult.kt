package com.ccu.wulpass.rest.data

import com.google.gson.annotations.SerializedName

class AccessResult(@SerializedName("success")val success: Boolean, @SerializedName("message")val message:String) {
}