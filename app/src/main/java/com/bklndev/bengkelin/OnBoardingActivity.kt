package com.bklndev.bengkelin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.hololo.tutorial.library.Step
import com.hololo.tutorial.library.TutorialActivity


class OnBoardingActivity : TutorialActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        addFragment(
            Step.Builder().setTitle("Find Repair Shop")
                .setContent("Best partner for a bad situation.Don't worry,fix it yourself or search")
                .setBackgroundColor(Color.parseColor("#101727")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("This is summary")
                .build()
        )
        addFragment(
            Step.Builder().setTitle("Responsive Mechanic")
                .setContent("We provide services instruction to repair your vehicle easily")
                .setBackgroundColor(Color.parseColor("#101727")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("This is summary")
                .build()
        )
        addFragment(
            Step.Builder().setTitle("Enjoy Your Ride and Drive Safety")
                .setContent("#BenerinYaBengkelin")
                .setBackgroundColor(Color.parseColor("#101727")) // int background color
                .setDrawable(R.drawable.onboard3) // int top drawable
                .setSummary("This is summary")
                .build()
        )

    }

    override fun finishTutorial() {
//        Toast.makeText(this, "Get Started", Toast.LENGTH_SHORT).show()
        nextToWelcome()
    }

    override fun currentFragmentPosition(position: Int) {

    }

    private fun nextToWelcome() {
        intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}