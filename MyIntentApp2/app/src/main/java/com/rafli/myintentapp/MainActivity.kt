package com.rafli.myintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPindahAktifitas: Button = findViewById(R.id.btn_move_activity)
        btnPindahAktifitas.setOnClickListener(this)
        val btnPindahData:Button = findViewById(R.id.move_with_data)
        btnPindahData.setOnClickListener(this)
        val btnPindahObjek:Button = findViewById(R.id.btn_move_object)
        btnPindahObjek.setOnClickListener(this)
    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.btn_move_activity->{
                val moveIntent = Intent(this@MainActivity,MainActivity2::class.java)
                startActivity(moveIntent);
            }
            R.id.move_with_data->{
                val moveIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveIntent.putExtra(MoveWithDataActivity.EXTRA_NAME,"Rafli iskandar Kavarera")
                moveIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 18)
                startActivity(moveIntent)
            }

            R.id.btn_move_object->{
                val person = Person(
                    "UDIN",5,"dicoding@admin.com","Jakarta")
                val moveIntent = Intent(this@MainActivity, MoveWithObject::class.java)
                moveIntent.putExtra(MoveWithObject.EXTRA_PERSON, person)
                Toast.makeText(this,"${person.City} ${person.Email}",Toast.LENGTH_LONG).show()
                startActivity(moveIntent)
            }

        }
    }
}