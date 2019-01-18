package com.richzjc.notification

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView?.adapter = NotificationAdapter.instance
        start.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(isNotificationListenerEnable()){
            val intent = Intent(this, MyNotificationListenerService::class.java)
            startService(intent)
            Toast.makeText(this, "启动服务成功", Toast.LENGTH_SHORT).show()
        }else{
            openNotificationSettings()
        }
    }

     fun isNotificationListenerEnable() : Boolean{
         val packageNames = NotificationManagerCompat.getEnabledListenerPackages(this)
         return packageNames.contains(packageName)
     }

    fun openNotificationSettings(){
        try {
            var intent : Intent
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
              intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            }else{
                intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            }
            startActivity(intent)
        } catch (e: Exception) {
        }
    }

}
