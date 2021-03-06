package com.rafli.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.content.Intent
import android.net.Uri

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity:Button= findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnDialNumber:Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.btn_move_activity->{
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data->{
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Rafli Ganteng")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE,5)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_dial_number->{
                val phNumber = "*123#"
                startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:$phNumber")))
            }
        }
    }
}