package com.bklndev.bengkelin_prototype2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bklndev.bengkelin_prototype2.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        Glide.with(this).load("http://via.placeholder.com/300.png").into(iv_hello)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
