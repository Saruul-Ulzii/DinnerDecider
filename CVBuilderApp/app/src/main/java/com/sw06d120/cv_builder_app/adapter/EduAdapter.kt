package com.codingstuff.scrollview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.EduItem

class EduAdapter(eduItems: List<EduItem>) :
    RecyclerView.Adapter<EduAdapter.EduViewHolder>() {
    private val eduList: List<EduItem>

    init {
        this.eduList = eduItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EduViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.about_me_education_item, parent, false)
        return EduViewHolder(view)
    }

    override fun onBindViewHolder(holder: EduViewHolder, position: Int) {
        holder.mUnivName.setText(eduList[position].name)
        holder.mUnivDegree.setText(eduList[position].degree)
        holder.mUnivLogo.setImageResource(eduList[position].image)
    }

    override fun getItemCount(): Int {
        return eduList.size
    }

    inner class EduViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mUnivLogo: ImageView
        val mUnivName: TextView
        val mUnivDegree: TextView

        init {
            mUnivLogo = itemView.findViewById(R.id.contact_logo)
            mUnivName = itemView.findViewById(R.id.contact_name)
            mUnivDegree = itemView.findViewById(R.id.contact_value)
        }
    }
}