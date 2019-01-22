package com.richzjc.notification.util

import android.content.ContentValues
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.service.notification.StatusBarNotification
import android.util.Log
import com.richzjc.notification.db.DataBaseHelper
import com.richzjc.notification.model.NotificationEntity
import com.richzjc.notification.packageNames

var allAppList : List<PackageInfo>? = null

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
    return if (packageNames == null || packageNames!!.isEmpty() || packageName == null)
        true
    else {
        var flag: Boolean = false
        packageNames?.forEach {
            if (packageName.contains(it.trim()) && it.isNotEmpty()) {
                flag = true
                return@forEach
            }
        }
        flag
    }
}

fun getAllApp(context : Context) : List<PackageInfo>? {
    allAppList =  context.applicationContext.packageManager.getInstalledPackages(0).filter {
        val flag = (it.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM === 0)
        flag
    }
    return allAppList
}

fun getPackageName(list : List<PackageInfo>?, sbn : StatusBarNotification?) : String{
    var packageName : String = ""
    if(sbn == null)
        return packageName
    packageName = sbn.packageName
    list?.forEach {
        if(sbn.groupKey!!.trim().contains(it.packageName.trim())){
            packageName = it.packageName.trim()
            return@forEach
        }
    }
    return packageName
}