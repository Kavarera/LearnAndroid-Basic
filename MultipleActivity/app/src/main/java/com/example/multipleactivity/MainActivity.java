package com.example.multipleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_activity1,btn_activity2,btn_activity3,btn_selesai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_activity1 = (Button) findViewById(R.id.btn_Activity1);
        btn_activity1 = (Button) findViewById(R.id.btn_Activity2);
        btn_activity3 = (Button) findViewById(R.id.btn_Activity3);
        btn_selesai = (Button) findViewById(R.id.btn_Selesai);

        //Method when button1 is pressed
        btn_activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call another activity
                Intent intent = new Intent(MainActivity.this,Activity1.class);
                startActivity(intent);
            }
        });

        //Method when button2 is pressed
        btn_activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call another activity
                Intent aktifitas = new Intent(MainActivity.this,Activity2.class);
                startActivity(aktifitas);
            }
        });

        btn_activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            }
        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}