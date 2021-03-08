package com.ccu.wulpass.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ccu.wulpass.R
import com.ccu.wulpass.rest.connection.SendData
import com.ccu.wulpass.ui.contract.ContractFragment
import com.ccu.wulpass.ui.id.IDFragment
import com.ccu.wulpass.ui.wallet.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavActivity : AppCompatActivity(),SendData {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_fragment)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->{
                    replaceFragment(IDFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard->{
                    replaceFragment(WalletFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications->{
                    replaceFragment(ContractFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else->{
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main, fragment)
        fragmentTransaction.commit()
    }

    override fun passData(tag: String, data: String, target: Fragment) {
        val bundle = Bundle();
        bundle.putString(tag,data)
        val transaction = this.supportFragmentManager.beginTransaction()

        target.arguments = bundle
        transaction.replace(R.id.content_id, target)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun nextActivity(tag: String,data: String, target: Class<NavActivity>) {
        val intent = Intent(applicationContext, target)
        intent.putExtra(tag,data)
        startActivity(intent)
    }
}