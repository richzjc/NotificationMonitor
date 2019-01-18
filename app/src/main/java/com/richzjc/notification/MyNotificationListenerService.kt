package com.richzjc.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.os.Build
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

@SuppressLint("OverrideAbstract")
class MyNotificationListenerService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        val n = sbn?.notification
        val title = n?.tickerText?.toString()
        var bundle : Bundle? = null
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2){
            try {
                val field = Notification::class.java.getDeclaredField("extras")
                bundle = field?.get(n) as? Bundle
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }else if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            bundle = n?.extras
        }

        val contentText = bundle?.getString(Notification.EXTRA_TEXT)
        val contentSubText = bundle?.getString(Notification.EXTRA_SUB_TEXT)
        val entity = NotificationEntity()
        entity.title = title
        entity.content = contentText
        entity.subContent = contentSubText
        NotificationAdapter.list.add(entity)
        NotificationAdapter.notifyItemInserted(NotificationAdapter.list.size - 1)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}