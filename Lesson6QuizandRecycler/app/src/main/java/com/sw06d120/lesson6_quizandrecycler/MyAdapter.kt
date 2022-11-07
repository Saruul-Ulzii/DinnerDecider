package com.sw06d120.lesson6_quizandrecycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item.view.*

class MyAdapter(var blist:ArrayList<Item>, val itemSelectionHandler:ItemSelectionListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.itemView.name.text = blist[position].title
        holder.itemView.price.text = "Price: $${blist[position].price.toString()}"
        holder.itemView.color.text = "Color: ${blist[position].color}"

        val context: Context = holder.itemView.photo.getContext()
        val id: Int = context.getResources().getIdentifier(blist[position].image, "drawable", context.getPackageName())
        holder.itemView.photo.setImageResource(id)
    }

    override fun getItemCount(): Int {
        return blist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                itemSelectionHandler.onItemClick(adapterPosition)
            }
        }
    }
    interface ItemSelectionListener{
        fun onItemClick(position: Int)
    }
}