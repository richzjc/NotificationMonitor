package com.richzjc.notification.util

import android.content.ContentValues
import android.util.Log
import com.richzjc.notification.db.DataBaseHelper
import com.richzjc.notification.model.NotificationEntity

fun insert(dbHelpter : DataBaseHelper, entity : NotificationEntity){
    val values = ContentValues()
    values.put("title",entity.title)
    values.put("content",entity.content)
    values.put("subContent",entity.subContent)
    values.put("flag",entity.flag)
    dbHelpter.writableDatabase?.insert("notification", null, values)
    val cursor = dbHelpter.writableDatabase.query("notification", null, null, null, null, null, null)
    Log.i("size", "${cursor.count}")
}