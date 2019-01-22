package com.richzjc.notification.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.richzjc.notification.R
import com.richzjc.notification.adapter.AppListAdapter
import com.richzjc.notification.packageNames
import com.richzjc.notification.service.MyNotificationListenerService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView?.adapter = AppListAdapter(this)
        start.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (isNotificationListenerEnable()) {
            val notificationIntent = Intent(this, NotificationListActivity::class.java)
            startActivity(notificationIntent)
            val intent = Intent(this, MyNotificationListenerService::class.java)
            startService(intent)
            Toast.makeText(this, "启动服务成功", Toast.LENGTH_SHORT).show()
            packageNames = et?.text?.toString()?.trim()?.let {
                it.split(",")
            }
        } else {
            openNotificationSettings()
        }
    }

    private fun isNotificationListenerEnable(): Boolean {
        val packageNames = NotificationManagerCompat.getEnabledListenerPackages(this)
        return packageNames.contains(packageName)
    }

    private fun openNotificationSettings() {
        try {
            Toast.makeText(this, "请先打开设置里面对应app的开关，然后在重新启动服务", Toast.LENGTH_SHORT).show()
            var intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            } else {
                Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
