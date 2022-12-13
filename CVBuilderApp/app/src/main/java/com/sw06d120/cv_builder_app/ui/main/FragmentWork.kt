package com.sw06d120.cv_builder_app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingstuff.scrollview.Adapter.WorkExperienceAdapter
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.WorkExperienceItem
import kotlinx.android.synthetic.main.fragment_work.view.*

class FragmentWork : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_work, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.contactList.setHasFixedSize(true)
        view.contactList.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))

        val workList: ArrayList<WorkExperienceItem> = ArrayList<WorkExperienceItem>()
        workList.add(
            WorkExperienceItem(
                R.drawable.com_mb,
                "Senior iOS Developer",
                "Mercedes-Benz Financial Services USA LLC",
                "Oct 2022 - Present ",
                "Farmington Hills, MI",
                "Fullstack Senior iOS developer - Work on Java Spring backend, iOS Frontend projects"
            )
        )
        workList.add(
            WorkExperienceItem(
                R.drawable.com_eys,
                "Full Stack Developer",
                "2nd Community Systems LLC",
                "Jun 2020 - Sep 2021",
                "Ulaanbaatar, Mongolia",
                "Contributed to front-end and back-end development of software systems and iOS applications"
            )
        )
        workList.add(
            WorkExperienceItem(
                R.drawable.com_nomch,
                "Senior Full Stack Developer",
                "Nomch IT Consulting LLC",
                "Apr 2017 - Dec 2019",
                "Ulaanbaatar, Mongolia",
                "Worked closely with other developers, UX designers, and system analysts during research, design, and management of servers, databases, backend, and iOS native apps."
            )
        )
        workList.add(
            WorkExperienceItem(
                R.drawable.com_mid,
                "Senior Full Stack Developer",
                "Mongol iD",
                "Apr 2015 - May 2016",
                "Ulaanbaatar, Mongolia",
                "Used a variety of technologies to develop frontend, backend, and iOS native apps."
            )
        )
        workList.add(
            WorkExperienceItem(
                R.drawable.com_itzone,
                "Senior Software Developer",
                "Mercedes-Benz Financial Services USA LLC",
                "Sep 2010 - Apr 2015 ",
                "Ulaanbaatar, Mongolia",
                "Coded and tested programming for software and mobile apps."
            )
        )

        val workExpAdapter = WorkExperienceAdapter(workList)
        view.contactList.adapter = workExpAdapter
    }
}
