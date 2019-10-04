package com.bklndev.bengkelin_prototype2

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bklndev.bengkelin_prototype2.fragment.AccountFragment
import com.bklndev.bengkelin_prototype2.fragment.ChatFragment
import com.bklndev.bengkelin_prototype2.fragment.HomeFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragmentHome()

        dash_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dash_nav_home -> {
                    createFragmentHome()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.dash_nav_chat -> {
                    createFragmentChat()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.dash_nav_account -> {
                    createFragmentAccount()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun createFragmentHome(){
        val transaction = manager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createFragmentChat(){
        val transaction = manager.beginTransaction()
        val fragment = ChatFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun createFragmentAccount(){
        val transaction = manager.beginTransaction()
        val fragment = AccountFragment()
        transaction.replace(R.id.dash_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
