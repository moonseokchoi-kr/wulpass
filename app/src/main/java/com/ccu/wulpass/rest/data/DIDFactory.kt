package com.ccu.wulpass.rest.data

import android.util.Base64
import com.ccu.wulpass.encryption.PemFactory
import java.util.*

object DIDFactory {

    fun createRequest(user:String,orgName:String):DIDRequest{
        val create = (System.currentTimeMillis()/1000L).toString()
        val id = "wul:did:$user"
        PemFactory.generatePKI(orgName)
        val pubKey = PemFactory.getBase64key(orgName)
        val auth = PemFactory.signContract(user, orgName)
        return DIDRequest(id,create, pubKey, auth)
    }
    fun createContract(user:String, orgName:String, data: String, sign: String):Contract{
        val uuid = UUID.randomUUID().toString()
        val create = (System.currentTimeMillis()/1000L).toString()
        val id = "wul:did:$user"
        val seller = "did:wul:YWZiZGZlMDAtYWJlNy00ZmY0LWEyYjUtZWEzZjEyZmZiOWJasdwqdaa"
        val context = "https://www.joongna.com/"
        val contract = PemFactory.encryptContract(data)
        return Contract(context, uuid,seller, id, create,contract,sign)
    }
}