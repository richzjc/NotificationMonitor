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
import com.richzjc.notification.util.isNotificationEnable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var serviceIntent: Intent? = null
    var disPosable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermiss()
        recycleView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView?.adapter = AppListAdapter(this)
        start.setOnClickListener(this)
    }

    private fun checkPermiss(){
        if(isNotificationEnable(this)){
            serviceIntent = Intent(this, MyNotificationListenerService::class.java)
            startService(serviceIntent)
            start.visibility = View.GONE
        }else{
            openNotificationSettings()
            Toast.makeText(this, "请先打开设置里面对应app的开关，只有打开了开关才能收到推送", Toast.LENGTH_LONG).show()
            addObserble()
        }
    }

    private fun addObserble(){
       disPosable = Observable.interval(300, TimeUnit.MILLISECONDS)
                .takeUntil { isNotificationEnable(this) }
                .doOnNext {
                    serviceIntent = serviceIntent ?: Intent(this, MyNotificationListenerService::class.java)
                    startService(serviceIntent)
                    start.visibility = View.GONE
                }
                .subscribe()
    }

    override fun onClick(v: View?) {
        if (isNotificationEnable(this)) {
            val notificationIntent = Intent(this, NotificationListActivity::class.java)
            startActivity(notificationIntent)
            packageNames = et?.text?.toString()?.trim()?.let {
                if(it.isEmpty())
                    null
                else {
                    val list = it.split(",")
                    list.forEach { it.trim() }
                    list
                }
            }
        } else {
            openNotificationSettings()
        }
    }

    private fun openNotificationSettings() {
        try {
            Toast.makeText(this, "请先打开设置里面对应app的开关，只有打开了开关才能收到推送", Toast.LENGTH_LONG).show()
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

    override fun onDestroy() {
        super.onDestroy()
        if(serviceIntent != null)
            stopService(serviceIntent)

        if(disPosable != null && !disPosable!!.isDisposed)
            disPosable!!.dispose()
    }
}
