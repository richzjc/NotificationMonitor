package com.richzjc.notification.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.richzjc.notification.R
import com.richzjc.notification.model.NotificationEntity

class NotificationHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val tv : TextView = itemView.findViewById(R.id.textView)
    fun doBindData(entity: NotificationEntity){
        tv?.text = "packageName = ${entity?.packageName} \n\ntitle = ${entity?.title}, \n\ncontent = ${entity?.content}, \n\nsubContent = ${entity.subContent}"
    }
}