package com.ccu.wulpass.encryption

import android.util.Base64
import java.util.*

class IDEncryption() {

    private var uuid: String

    init {
        uuid = createUUID()
    }

    private fun createUUID(): String{
        val uuid = UUID.randomUUID().toString();
        val tmp = uuid.toByteArray();
        return String(Base64.encode(tmp,Base64.URL_SAFE)).replace("\\r\\n|\\r|\\n|\\n\\r".toRegex(),"")
    }
    fun getUUID():String{
        return uuid;
    }
}