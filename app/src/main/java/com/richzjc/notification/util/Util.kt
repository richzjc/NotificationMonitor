package com.richzjc.notification.util

import android.content.Context
import android.support.v4.app.NotificationManagerCompat

fun isNotificationEnable(context : Context) : Boolean{
    val packageNames = NotificationManagerCompat.getEnabledListenerPackages(context)
    return packageNames.contains(context.packageName)
}