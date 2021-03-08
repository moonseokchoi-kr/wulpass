package com.ccu.wulpass

import android.util.Log
import com.ccu.wulpass.encryption.PemFactory
import com.ccu.wulpass.rest.data.LoginInfo
import com.ccu.wulpass.rest.repo.UserRepo
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun encrypt(){
        PemFactory.generatePKI("Org1")
        val test = PemFactory.encryptContract("it'test","Org1")
        Log.d("DEBUG",test)
        Log.d("DEBUG",PemFactory.decrpytContract(test,"Org1"))

    }
}