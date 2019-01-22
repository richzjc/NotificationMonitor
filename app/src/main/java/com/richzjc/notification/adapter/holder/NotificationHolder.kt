package com.richzjc.notification.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.richzjc.notification.model.NotificationEntity

class NotificationHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun doBindData(entity: NotificationEntity){
        (itemView as? TextView)?.text = "title = ${entity?.title}, \n\ncontent = ${entity?.content}, \n\nsubContent = ${entity.subContent}"
    }
}