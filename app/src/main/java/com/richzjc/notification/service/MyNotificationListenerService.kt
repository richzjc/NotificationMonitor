package com.richzjc.notification.service

import android.annotation.SuppressLint
import android.app.Notification
import android.os.Build
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.richzjc.notification.adapter.NotificationAdapter
import com.richzjc.notification.db.DataBaseHelper
import com.richzjc.notification.model.NotificationEntity
import com.richzjc.notification.util.allAppList
import com.richzjc.notification.util.containsPackageName
import com.richzjc.notification.util.insert

@SuppressLint("OverrideAbstract")
class MyNotificationListenerService : NotificationListenerService() {

    var dbHelper = DataBaseHelper(this)

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        val n = sbn?.notification
        val title = n?.tickerText?.toString() ?: ""
        val flag = n?.flags ?: -1
        var bundle: Bundle? = null
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                val field = Notification::class.java.getDeclaredField("extras")
                bundle = field?.get(n) as? Bundle
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bundle = n?.extras
        }
        val contentText = bundle?.getString(Notification.EXTRA_TEXT) ?: ""
        val contentSubText = bundle?.getString(Notification.EXTRA_SUB_TEXT) ?: ""
        val entity = NotificationEntity()
        entity.title = title
        entity.content = contentText
        entity.subContent = contentSubText
        entity.flag = flag
        entity.packageName = com.richzjc.notification.util.getPackageName(allAppList, sbn)
        if (containsPackageName(entity.packageName)) {
            NotificationAdapter.list.add(0, entity)
            NotificationAdapter.notifyItemInserted(0)
            insert(dbHelper, entity)
        }

    }
}