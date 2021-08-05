package com.example.swu_guru

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GroupBuyingDBManager(context: Context) : SQLiteOpenHelper(context, "gbDB", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("PRAGMA foreign_keys = 1;")

        db!!.execSQL("CREATE TABLE writing (" + "WId INTEGER, " + "title text, " +
                "content text, " + "price INTEGER, " + "person INTEGER, " +
                "count INTEGER, " + "image BLOB, " + "tag INTEGER, " + "PRIMARY KEY(WId AUTOINCREMENT));")

        db!!.execSQL("CREATE TABLE user (" + "UId INTEGER, " + "id text, " + "password text, " +
                "image BLOB, " + "nickname text, " + "PRIMARY KEY(UId AUTOINCREMENT));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}