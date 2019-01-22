package com.richzjc.notification.util

import android.content.ContentValues
import android.text.TextUtils
import android.util.Log
import com.richzjc.notification.db.DataBaseHelper
import com.richzjc.notification.model.NotificationEntity
import com.richzjc.notification.packageNames

fun insert(dbHelpter: DataBaseHelper, entity: NotificationEntity) {
    val values = ContentValues()
    values.put("title", entity.title)
    values.put("content", entity.content)
    values.put("subContent", entity.subContent)
    values.put("flag", entity.flag)
    dbHelpter.writableDatabase?.insert("notification", null, values)
    val cursor = dbHelpter.writableDatabase.query("notification", null, null, null, null, null, null)
    Log.i("size", "${cursor.count}")
}

fun containsPackageName(packageName: String?): Boolean {
    return if (packageNames == null || packageNames!!.isEmpty())
        true
    else {
        var flag : Boolean = true
        packageNames?.forEach {
            if(TextUtils.equals(it.trim(), packageName?.trim())){
                flag = true
                return@forEach
            }
        }
        flag
    }

}