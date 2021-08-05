package com.example.swu_guru

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class DeliList : AppCompatActivity() {

    //배달 리스트
    lateinit var delidbManager: DeliDBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delilist)


        //배달 db 연결
        delidbManager= DeliDBManager(this,"delipersonnelDB",null,1)
        sqlitedb=delidbManager.readableDatabase

        layout=findViewById(R.id.delipersonnel)

        //cursor 이용해서, 저장된 배달 리스트들 불러오기
        var cursor : Cursor
        cursor=sqlitedb.rawQuery("SELECT * FROM delipersonnel",null)
        var num : Int=0
        while (cursor.moveToNext()){
            var str_title = cursor.getString(cursor.getColumnIndex("title")).toString()
            var price = cursor.getInt((cursor.getColumnIndex("price")))
            var people = cursor.getInt((cursor.getColumnIndex("people")))
            var str_content = cursor.getString(cursor.getColumnIndex("content")).toString()


            var layout_item:LinearLayout= LinearLayout(this)
            layout_item.orientation=LinearLayout.VERTICAL
            layout_item.id=num

            var tvTitle  : TextView = TextView(this)
            tvTitle.text=str_title
            tvTitle.textSize=25f
            tvTitle.setBackgroundColor(Color.parseColor("#66CDAA"))
            layout_item.addView(tvTitle)

            var tvContent  : TextView = TextView(this)
            tvContent.text=str_content
            tvContent.textSize=20f
            layout_item.addView(tvContent)

            var tvPrice  : TextView = TextView(this)
            tvPrice.text=price.toString()
            tvPrice.textSize=20f
            layout_item.addView(tvPrice)


            var tvPeople  : TextView = TextView(this)
            tvPeople.text=people.toString()
            tvPeople.textSize=20f
            layout_item.addView(tvPeople)


            layout.addView(layout_item)
            num++;

        }

        cursor.close()
        sqlitedb.close()
        delidbManager.close()

    }
}