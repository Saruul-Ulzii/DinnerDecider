package com.codingstuff.scrollview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.ContactItem
import com.sw06d120.cv_builder_app.model.WorkExperienceItem

class ContactAdapter(eduItems: List<ContactItem>, val itemSelectionHandler: ItemSelectionListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private val contactList: List<ContactItem>

    init {
        this.contactList = eduItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.mContactLogo.setImageResource(contactList[position].image)
        holder.mContactName.setText(contactList[position].contact)
        holder.mContactValue.setText(contactList[position].value)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContactLogo: ImageView
        val mContactName: TextView
        val mContactValue: TextView

        init {
            mContactLogo = itemView.findViewById(R.id.contact_logo)
            mContactName = itemView.findViewById(R.id.contact_name)
            mContactValue = itemView.findViewById(R.id.contact_value)

            itemView.setOnClickListener{
                itemSelectionHandler.onItemClick(adapterPosition)
            }
        }
    }

    interface ItemSelectionListener{
        fun onItemClick(position: Int)
    }
}