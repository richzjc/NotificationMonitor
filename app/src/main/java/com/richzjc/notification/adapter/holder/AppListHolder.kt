package com.richzjc.notification.adapter.holder

import android.content.pm.ApplicationInfo
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.richzjc.notification.R
import com.richzjc.notification.model.NotificationEntity

class AppListHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val packageName = itemView.findViewById<TextView>(R.id.app_package_name)
    val appName = itemView.findViewById<TextView>(R.id.app_name)


    fun doBindData(entity: ApplicationInfo){
        packageName.text = entity.packageName
    }
}