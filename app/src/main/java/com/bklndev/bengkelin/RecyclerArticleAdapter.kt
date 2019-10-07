package com.bklndev.bengkelin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.articles_item.view.*

class RecyclerArticleAdapter(private val title: Array<String>, private val section: Array<String>)
    : RecyclerView.Adapter<RecyclerArticleAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_item,parent,false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_article_title.text = title[position]
        holder.itemView.tv_article_section.text = section[position]
    }

    override fun getItemCount() = title.size
}