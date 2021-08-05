package com.example.swu_guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DeliveryIntroActivity : AppCompatActivity() {

    lateinit var foodsearchBtn : Button
    lateinit var foodupBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_intro)


        foodsearchBtn = findViewById(R.id.foodsearchBtn)
        foodsearchBtn.setOnClickListener{
            var intent = Intent(this, DeliList::class.java)
            startActivity(intent)

        }


        foodupBtn = findViewById(R.id.foodupBtn)
        foodupBtn.setOnClickListener{
            var intent = Intent(this, DeliWrite::class.java)
            startActivity(intent)

        }






    }
}