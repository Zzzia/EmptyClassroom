package com.zia.emptyclassroom.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zia.emptyclassroom.Config
import com.zia.emptyclassroom.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        about_contact.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Config.github))
            startActivity(intent)
        }
    }
}
