package com.rafli.intentapp2

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveObject : AppCompatActivity() {

    companion object{
        const val EXTRA_ORANG = "extra_orang"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_object)
        val tvObject:TextView = findViewById(R.id.tv_object)
        val person=intent.getParcelableExtra<Orang>(EXTRA_ORANG) as Orang
        tvObject.text = "Nama = ${person.Nama.toString()}\n" +
                "Umur = ${person.Umur}\n" +
                "Email = ${person.Email}\n" +
                "Kota = ${person.Kota}"
    }
}