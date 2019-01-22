package com.richzjc.notification.adapter.holder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageInfo
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.richzjc.notification.R


class AppListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val packageName = itemView.findViewById<TextView>(R.id.app_package_name)
    val appName = itemView.findViewById<TextView>(R.id.app_name)


    fun doBindData(entity: PackageInfo) {
        packageName.text = "包名： ${entity.packageName}"
        appName.text = "名称：${entity.applicationInfo.loadLabel(itemView.context.packageManager)}"

        itemView.setOnClickListener {
            val cmb = itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(entity.packageName, entity.packageName)
            cmb.primaryClip = clip
            Toast.makeText(it.context, "已复制", Toast.LENGTH_SHORT).show()
        }
    }
}