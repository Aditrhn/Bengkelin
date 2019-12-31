package com.bklndev.bengkelin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.bklndev.bengkelin.R
import com.bklndev.bengkelin.RecyclerArticleAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    val title = arrayOf("Cara Begal Motor","Cara Menghindari Cegatan dan Roadblock Heat level 5", "Cheat Anti Polisi Terbaru","Cara Memilih Tromol")
    val section = arrayOf("Tips", "Tips", "News", "Tips")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? 
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        glideLoad(view)

        rv_article.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rv_article.adapter = RecyclerArticleAdapter(title,section)
    }

    private fun glideLoad(view: View){
        Glide.with(view)
            .load("https://github.com/Makrovic/Bengkelin_Prototype2/raw/master/amel2.jpg")
            .apply(RequestOptions.circleCropTransform())
            .into(iv_hello)
    }
}