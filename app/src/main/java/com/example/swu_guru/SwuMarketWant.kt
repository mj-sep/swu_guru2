package com.example.swu_guru

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swu_guru.databinding.ActivitySwuMarketWantBinding

class SwuMarketWant : AppCompatActivity() {
    val binding by lazy { ActivitySwuMarketWantBinding.inflate(layoutInflater) }
    lateinit var dbManager: MarketDBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var mtitle : String
    lateinit var mcost : String
    lateinit var buyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbManager = MarketDBManager(this)

        buyButton = findViewById(R.id.buyButton)

        val data:MutableList<ListLayout> = loadData()
        var adapter = MarketWantListAdapter()
        adapter.itemList = data

        binding.marketList.adapter = adapter
        binding.marketList.layoutManager = LinearLayoutManager(this)

        //쓰기 버튼 클릭시 intent로 해당 버튼이 want의 쓰기임을 알려주면서 쓰기 페이지로 넘어가기

        binding.btnwrite.setOnClickListener{
            var intent = Intent(this, MarektWrite::class.java)
            intent.putExtra("key", "want")
            startActivity(intent)
        }
        //팔아요 버튼 클릭시 물건판매의 팔아요 페이지로 넘어감

        buyButton.setOnClickListener{
            var intent = Intent(this, SwuMarketActivity::class.java)
            startActivity(intent)
        }

    }
    // 원해요 db열어 목록 리사이클러뷰로 띄워주기
    fun loadData(): MutableList<ListLayout>{
        val data : MutableList<ListLayout> = mutableListOf()
        sqlitedb = dbManager.readableDatabase
        var cursor: Cursor

        cursor = sqlitedb.rawQuery("SELECT * FROM marketWantTBL;", null)
        if(cursor.moveToFirst()){
            do{
                mtitle = cursor.getString(cursor.getColumnIndex("title")).toString()
                mcost = cursor.getString(cursor.getColumnIndex("price")).toString()

                var title = mtitle
                var cost = mcost
                var listlayout = ListLayout(title, cost )
                data.add(listlayout)
            }while (cursor.moveToNext())
        }
        cursor.close()
        sqlitedb.close()

        return data;
    }

}