package com.rafli.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObject : AppCompatActivity() {

    companion object{
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObjects:TextView = findViewById(R.id.tv_object_received)
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON)as Person
        val textt = "Name: ${person.Name.toString()},Email: " +
                "${person.Email},\nAge: ${person.Age},\nCity: ${person.City}"
        tvObjects.text=textt
    }
}