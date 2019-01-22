package com.richzjc.notification.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.richzjc.notification.R
import com.richzjc.notification.adapter.NotificationAdapter.list
import com.richzjc.notification.adapter.holder.AppListHolder
import com.richzjc.notification.util.allAppList
import com.richzjc.notification.util.getAllApp


class AppListAdapter(context: Context) : RecyclerView.Adapter<AppListHolder>() {

    init {
        getAllApp(context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AppListHolder {
        return AppListHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_app, p0, false))
    }

    override fun getItemCount() = allAppList?.size ?: 0

    override fun onBindViewHolder(p0: AppListHolder, p1: Int) {
        p0.doBindData(allAppList!![p1])
        if (p1 % 2 == 0) {
            p0.itemView.setBackgroundColor(Color.WHITE)
        } else {
            p0.itemView.setBackgroundColor(Color.GREEN)
        }

    }
}