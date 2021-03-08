package com.ccu.wulpass.rest.connection

import androidx.fragment.app.Fragment
import com.ccu.wulpass.ui.main.NavActivity

interface SendData{
    fun passData(tag:String,data:String, target: Fragment);

    fun nextActivity(tag:String, data:String, target:Class<NavActivity>)
}