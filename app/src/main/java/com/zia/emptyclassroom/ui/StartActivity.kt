package com.zia.emptyclassroom.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@StartActivity, MainActivity::class.java))
        finish()
    }
}
