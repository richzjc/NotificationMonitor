package com.richzjc.notification

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

object NotificationAdapter : RecyclerView.Adapter<NotificationHolder>() {
    val list = mutableListOf<NotificationEntity>()
    val instance = this

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NotificationHolder {
        return NotificationHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_notification, p0, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: NotificationHolder, p1: Int) {
        p0.doBindData(list[p1])
        if(p1%2 == 0){
            p0.itemView.setBackgroundColor(Color.WHITE)
        }else{
            p0.itemView.setBackgroundColor(Color.GREEN)
        }

    }
}