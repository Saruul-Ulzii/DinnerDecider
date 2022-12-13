package com.codingstuff.scrollview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.WorkExperienceItem

class WorkExperienceAdapter(eduItems: List<WorkExperienceItem>) :
    RecyclerView.Adapter<WorkExperienceAdapter.WorkExperienceViewHolder>() {
    private val workList: List<WorkExperienceItem>

    init {
        this.workList = eduItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkExperienceViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.work_experience_item, parent, false)
        return WorkExperienceViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkExperienceViewHolder, position: Int) {
        holder.mWorkName.setText(workList[position].workName)
        holder.mCompanyName.setText(workList[position].companyName)
        holder.mWorkDate.setText(workList[position].workDate)
        holder.mWorkLocation.setText(workList[position].workLocation)
        holder.mWorkDescription.setText(workList[position].description)
        holder.mWorkLogo.setImageResource(workList[position].image)
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    inner class WorkExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mWorkLogo: ImageView
        val mWorkName: TextView
        val mCompanyName: TextView
        val mWorkDate: TextView
        val mWorkLocation: TextView
        val mWorkDescription: TextView

        init {
            mWorkLogo = itemView.findViewById(R.id.contact_logo)
            mWorkName = itemView.findViewById(R.id.contact_name)
            mCompanyName = itemView.findViewById(R.id.contact_value)
            mWorkDate = itemView.findViewById(R.id.work_date)
            mWorkLocation = itemView.findViewById(R.id.work_location)
            mWorkDescription = itemView.findViewById(R.id.description)
        }
    }
}