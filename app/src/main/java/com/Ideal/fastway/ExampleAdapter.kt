package com.Ideal.fastway

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(private val exampleList: List<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
/*
* adapter creates view holder and extract data from data set
* layout Manager knows how to position the rows and how we can scroll between 'em
* the recycler view itself is the container of the list but donno how to get this data
* how to set it or how to represent it so we need to couple our components
* it tells the others what to do ? at which point of time? =>   THE orchestrator
* it takes care also of recycling
*
* */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent,false)
        return  ExampleViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
    val currentItem=exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text=currentItem.text1
        holder.textView2.text=currentItem.text2

    }
    override fun getItemCount()= exampleList.size
        /* how many item in my list same as my list size*/



    class ExampleViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        //to find the view
    val imageView:ImageView=itemView.image_view
        val textView1:TextView=itemView.text_view_1
        val textView2:TextView=itemView.text_view_2
    }
}