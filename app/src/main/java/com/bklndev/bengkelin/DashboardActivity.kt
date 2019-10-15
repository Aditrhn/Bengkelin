package com.bklndev.bengkelin

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.bklndev.bengkelin.fragment.AccountFragment
import com.bklndev.bengkelin.fragment.ChatFragment
import com.bklndev.bengkelin.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        createFragmentHome()

        dash_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_nav_home -> {
                    createFragmentHome()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.item_nav_chat -> {
                    createFragmentChat()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.item_nav_account -> {
                    createFragmentAccount()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun createFragmentHome(){
        val transaction = manager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createFragmentChat(){
        val transaction = manager.beginTransaction()
        val fragment = ChatFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createFragmentAccount(){
        val transaction = manager.beginTransaction()
        val fragment = AccountFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
