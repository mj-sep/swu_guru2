package com.example.swu_guru

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DeliWrite : AppCompatActivity() {
    //배달음식 작성 페이지

    lateinit var deliDBmanager: DeliDBManager
    lateinit var sqlitedb : SQLiteDatabase


    lateinit var edttitle : EditText
    lateinit var edtprice : EditText
    lateinit var edtpeople : EditText
    lateinit var edtcontent : EditText

    lateinit var btnInt : Button



    private val TAG=DeliWrite::class.java.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deliwrite)

        edttitle =findViewById(R.id.titleArea)
        edtprice =findViewById(R.id.priceArea)
        edtpeople = findViewById(R.id.peopleArea)
        edtcontent = findViewById(R.id.contentArea)

        btnInt=findViewById(R.id.enterBtn)

        //배달 db 열고, 입력된 값 저장
        deliDBmanager= DeliDBManager(this,"delipersonnelDB",null,1)


        btnInt.setOnClickListener{

            var str_title: String=edttitle.text.toString()
            var str_price: String=edtprice.text.toString()
            var str_people: String=edtpeople.text.toString()
            var str_content: String=edtcontent.text.toString()


            sqlitedb = deliDBmanager.writableDatabase
            sqlitedb.execSQL("INSERT INTO delipersonnel VALUES ('"+str_title+"',"+str_price+",'"+str_people+"','"+str_content+"')")
            sqlitedb.close()

            val intent = Intent(this,DeliList::class.java)
            intent.putExtra("intent_name",str_title)
            startActivity(intent)


            Toast.makeText(applicationContext,"가격은"+edtprice.text.toString()+"(원)이고"+"\r\n"+"인원은"+edtpeople.text.toString()+"(명) 입니다",
                Toast.LENGTH_SHORT).show()
            finish()

        }


    }



}







