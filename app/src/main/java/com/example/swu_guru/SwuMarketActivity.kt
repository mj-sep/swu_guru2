package com.example.swu_guru

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swu_guru.databinding.ActivitySwuMarketBinding
import kotlinx.android.synthetic.main.activity_swu_market.*

//물건판매 메인 화면 ( 팔아요 )
class SwuMarketActivity : AppCompatActivity() {
    val binding by lazy { ActivitySwuMarketBinding.inflate(layoutInflater) }
    lateinit var dbManager: MarketDBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var mtitle : String
    lateinit var mcost : String
    lateinit var wantButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbManager = MarketDBManager(this)

        wantButton = findViewById(R.id.wantButton)

        val data:MutableList<ListLayout> = loadData()
        var adapter = ListAdapter()
        adapter.itemList = data

        binding.marketList.adapter = adapter
        binding.marketList.layoutManager = LinearLayoutManager(this)

        //쓰기 버튼 클릭시 intent로 해당 버튼이 buy의 쓰기임을 알려주면서 쓰기 페이지로 넘어가기
        binding.btnwrite.setOnClickListener{
            var intent = Intent(this, MarektWrite::class.java)
            intent.putExtra("key", "buy")
            startActivity(intent)
        }

        //원해요 버튼 클릭시 물건판매의 원해요 페이지로 넘어감
       wantButton.setOnClickListener{
            var intent = Intent(this, SwuMarketWant::class.java)
            startActivity(intent)
        }

    }
    // 팔아요의 db열어 목록 리사이클러뷰로 띄워주기
    fun loadData(): MutableList<ListLayout>{
        val data : MutableList<ListLayout> = mutableListOf()
        sqlitedb = dbManager.readableDatabase
        var cursor: Cursor

        cursor = sqlitedb.rawQuery("SELECT * FROM marketTBL;", null)
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