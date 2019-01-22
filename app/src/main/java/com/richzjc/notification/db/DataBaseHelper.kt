package com.richzjc.notification.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context, name : String, factory : SQLiteDatabase.CursorFactory?, version : Int) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table notification(title text, content text, subContent text, flag integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}