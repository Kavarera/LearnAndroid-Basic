package com.rafli.submissiondicoding_rafli

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class about_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title = "About Me"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_activity)
    }
}