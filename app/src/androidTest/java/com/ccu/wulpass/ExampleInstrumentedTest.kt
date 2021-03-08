package com.ccu.wulpass

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ccu.wulpass.rest.data.LoginInfo
import com.ccu.wulpass.rest.repo.UserRepo

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ccu.wulpass", appContext.packageName)
    }

    @Test
    fun register_user(){
        val userRepo = UserRepo()
        userRepo.registerUser(LoginInfo("Test","Org1")).subscribe {
            assertEquals(it.success,true)
            assertEquals(it.token,"did")
        }
    }
}