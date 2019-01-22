package com.richzjc.notification.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.richzjc.notification.R
import com.richzjc.notification.adapter.NotificationAdapter
import kotlinx.android.synthetic.main.activity_notification_list.*

class NotificationListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        recycleView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        NotificationAdapter.instance.list.clear()
        recycleView?.adapter = NotificationAdapter.instance
    }
}