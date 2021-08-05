package com.example.swu_guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    // val db = FirebaseFirestore.getInstance()
    lateinit var marketButton : Button
    lateinit var deliButton: Button
    lateinit var gbbtn : Button
    lateinit var borrowButton : Button
    lateinit var mypageButton: Button
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = intent.getStringExtra("id").toString()

        marketButton = findViewById(R.id.marketButton)
        marketButton.setOnClickListener{
            var intent = Intent(this, SwuMarketActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }



        deliButton = findViewById(R.id.deliButton)
        deliButton.setOnClickListener{
            var intent = Intent(this, DeliveryIntroActivity::class.java)
            startActivity(intent)

        }


        gbbtn = findViewById(R.id.gbbtn)
        gbbtn.setOnClickListener{
            var intent = Intent(this, GroupBuying::class.java)
            startActivity(intent)

        }

        borrowButton = findViewById(R.id.borrowButton)
        borrowButton.setOnClickListener{
            var intent = Intent(this, BorrowList::class.java)
            startActivity(intent)

        }

        mypageButton = findViewById(R.id.mypageButton)
        mypageButton.setOnClickListener{
            var intent = Intent(this, Mypage::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }
    }
}