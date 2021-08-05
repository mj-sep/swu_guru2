package com.example.swu_guru

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//물건 판매 글쓰기 페이지
class MarektWrite : AppCompatActivity() {
    lateinit var dbManager: MarketDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var mbtnUp: Button
    lateinit var mContent : EditText
    lateinit var mPrice : EditText
    lateinit var mTitle : EditText
    lateinit var mPlace : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_write)

        var data:String?
        data = intent.getStringExtra("key")

        mbtnUp = findViewById(R.id.mbtnUp)
        mContent = findViewById(R.id.mContent)
        mPrice = findViewById(R.id.mPrice)
        mTitle = findViewById(R.id.mTitle)
        mPlace = findViewById(R.id.mPlace)
        val REQUEST_READ_EXTERNAL_STORAGE = 1000

        dbManager = MarketDBManager(this)

        //MarketMain에서 쓰기 버튼 클릭 시 intent 값으로 buy를 가져와서 팔아요 db에 작성
        if(data == "buy") {
            mbtnUp.setOnClickListener {
                var title: String = mTitle.text.toString()
                var price: String = mPrice.text.toString()
                var content: String = mContent.text.toString()
                var place: String = mPlace.text.toString()

                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO marketTBL VALUES ('" + title + "', '" + content + "', " + price + ", '" + place + "');")
                //입력한 값 db에 저장

                //팔아요 메인으로 돌아가기
                val intent = Intent(this, SwuMarketActivity::class.java)
                startActivity(intent)

            }
        }

        //MarketWantMain 에서 쓰기 버튼 클릭 시 intent 값으로 want를 가져와서 원해요 db에 작성
        if(data == "want") {
            mbtnUp.setOnClickListener {
                var title: String = mTitle.text.toString()
                var price: String = mPrice.text.toString()
                var content: String = mContent.text.toString()
                var place: String = mPlace.text.toString()

                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO marketWantTBL VALUES ('" + title + "', '" + content + "', " + price + ", '" + place + "');")

                //want 메인으로 돌아가기
                val intent = Intent(this, SwuMarketWant::class.java)
                startActivity(intent)

            }
        }
    }

}