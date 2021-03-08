package com.ccu.wulpass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.ui.main.NavActivity
import com.ccu.wulpass.ui.splush.SplushFragment

class MainActivity : AppCompatActivity(), SendData {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_id, SplushFragment.newInstance())
                    .commitNow()
        }
    }

    override fun passData(tag: String, data: String,target: Fragment) {
        val bundle = Bundle();
        bundle.putString(tag,data)
        val transaction = this.supportFragmentManager.beginTransaction()

        target.arguments = bundle
        transaction.replace(R.id.content_id, target)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }


    override fun nextActivity(tag: String, data: String,target: Class<NavActivity>) {
        val intent = Intent(applicationContext, target)
        startActivity(intent)
    }
}