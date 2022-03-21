package com.rafli.intentapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class MoveWithData : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_AGE = "extra_age"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)
        val tvMoveWithData:TextView = findViewById(R.id.tv_move_with_data)
        tvMoveWithData.text="nama : ${intent.getStringExtra(EXTRA_NAME)}\n age : ${intent.getIntExtra(
            EXTRA_AGE,99)}"
    }
}