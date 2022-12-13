package com.sw06d120.cv_builder_app.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingstuff.scrollview.Adapter.ContactAdapter
import com.codingstuff.scrollview.Adapter.WorkExperienceAdapter
import com.sw06d120.cv_builder_app.R
import com.sw06d120.cv_builder_app.model.ContactItem
import com.sw06d120.cv_builder_app.model.WorkExperienceItem
import kotlinx.android.synthetic.main.fragment_work.view.*

class FragmentContact : Fragment(), ContactAdapter.ItemSelectionListener {
    val contactList: ArrayList<ContactItem> = ArrayList<ContactItem>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_contact, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.contactList.setHasFixedSize(true)
        view.contactList.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))

        contactList.add(
            ContactItem(
                R.drawable.con_phone,
                "+1 (641) 233 2391",
                "Mobile"
            )
        )
        contactList.add(
            ContactItem(
                R.drawable.con_email,
                "s.saruululzii@gmail.com",
                "Email"
            )
        )
        contactList.add(
            ContactItem(
                R.drawable.con_linkedin,
                "https://www.linkedin.com/in/saruululzii-sampilnorov/",
                "LinkedIn"
            )
        )
        contactList.add(
            ContactItem(
                R.drawable.con_git,
                "https://github.com/saruul-Ulzii/",
                "Github"
            )
        )

        val contactAdapter = ContactAdapter(contactList, this)
        view.contactList.adapter = contactAdapter
    }

    override fun onItemClick(position: Int) {
        if(position == 0) {
            val selectedIntent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${contactList[position].contact}")
            )
            startActivity(selectedIntent)
        }

        if(position == 1) {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "${contactList[position].contact}", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting from Resume app")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, have a good day!")
            startActivity(Intent.createChooser(emailIntent, "Send"))
        }

        if(position == 3) {
            val linkedIn = Intent(Intent.ACTION_VIEW)
            linkedIn.data = Uri.parse("${contactList[position].contact}")
            linkedIn.setPackage("com.linkedin.android")
            startActivity(linkedIn)
        }
    }
}
