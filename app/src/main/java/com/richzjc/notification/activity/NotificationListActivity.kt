package com.richzjc.notification.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.richzjc.notification.R
import com.richzjc.notification.adapter.NotificationAdapter
import com.richzjc.notification.service.MyNotificationListenerService
import kotlinx.android.synthetic.main.activity_notification_list.*

class NotificationListActivity : AppCompatActivity() {
    var serviceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        recycleView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        NotificationAdapter.instance.list.clear()
        recycleView?.adapter = NotificationAdapter.instance

        serviceIntent = Intent(this, MyNotificationListenerService::class.java)
        startService(serviceIntent)
        Toast.makeText(this, "启动服务成功", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceIntent?.let { stopService(it) }
    }
}