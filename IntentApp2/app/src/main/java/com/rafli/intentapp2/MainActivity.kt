package com.rafli.intentapp2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        private const val REQUEST_CODE = 100
    }

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnMoveActivity:Button = findViewById(R.id.btn_pindah_activity)
        btnMoveActivity.setOnClickListener(this)
        val btnMoveDataActivity:Button = findViewById(R.id.btn_pindah_data_activity)
        btnMoveDataActivity.setOnClickListener(this)
        val btnMoveObjectActivity:Button = findViewById(R.id.btn_pindah_object_activity)
        btnMoveObjectActivity.setOnClickListener(this)
        val btnDialNumber:Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)

        val btnMoveForResult:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)
        tvResult = findViewById(R.id.tv_result)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_pindah_activity->{
                val moveIntent = Intent(this@MainActivity,Activity2::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_pindah_data_activity->{
                val moveIntent= Intent(this@MainActivity,MoveWithData::class.java)
                moveIntent.putExtra(MoveWithData.EXTRA_NAME, "UDIN")
                moveIntent.putExtra(MoveWithData.EXTRA_AGE,4)
                startActivity(moveIntent)
            }
            R.id.btn_pindah_object_activity->{
                val moveIntent = Intent(this@MainActivity, MoveObject::class.java)
                moveIntent.putExtra(MoveObject.EXTRA_ORANG, Orang("Udin",20,"Udin@dicoding.com","London"))
                startActivity(moveIntent)
            }
            R.id.btn_dial_number->{
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:*123#"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result->{
                val moveForResultIntent = Intent(this@MainActivity, MoveForResult::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode==MoveForResult.RESULT_CODE){
                val selectedValue = data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE,0)
                tvResult.text="Hasil = $selectedValue"
            }
        }
    }
}