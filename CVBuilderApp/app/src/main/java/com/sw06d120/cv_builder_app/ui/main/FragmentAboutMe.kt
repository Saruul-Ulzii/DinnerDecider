package com.sw06d120.cv_builder_app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingstuff.scrollview.Adapter.EduAdapter
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.EduItem
import kotlinx.android.synthetic.main.fragment_about_me.*
import kotlinx.android.synthetic.main.fragment_about_me.view.*

class FragmentAboutMe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(com.sw06d120.cv_builder_app.R.layout.fragment_about_me, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.educationList.setHasFixedSize(true)
        view.educationList.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))

        val eduItemList: ArrayList<EduItem> = ArrayList<EduItem>()
        eduItemList.add(EduItem(R.drawable.logo_mahar, "Maharishi International University", "Master of Computer Science"))
        eduItemList.add(EduItem(R.drawable.logo_shutis, "Mongolian University of Science and Technology", "Master of Computer Science - 2012 (GPA 3.66)"))
        eduItemList.add(EduItem(R.drawable.logo_shutis, "Mongolian University of Science and Technology", "Bachelor of Computer Science - 2010 (GPA 3.83)"))

        val eduAdapter = EduAdapter(eduItemList)
        view.educationList.adapter = eduAdapter

        view.certificationList.setHasFixedSize(true)
        view.certificationList.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))

        val certItemList: ArrayList<EduItem> = ArrayList<EduItem>()
        certItemList.add(EduItem(R.drawable.cert_mcts, "MCTS - 2012", "Microsoft"))
        certItemList.add(EduItem(R.drawable.cert_itpec, "ITPEC - 2012", "Japan Embassy"))

        val certAdapter = EduAdapter(certItemList)
        view.certificationList.adapter = certAdapter
    }
}