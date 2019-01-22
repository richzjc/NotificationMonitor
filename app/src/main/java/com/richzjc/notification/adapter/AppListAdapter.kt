package com.richzjc.notification.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.richzjc.notification.R
import com.richzjc.notification.adapter.holder.AppListHolder

class AppListAdapter(context : Context) : RecyclerView.Adapter<AppListHolder>() {
    val list = context.packageManager.getInstalledApplications(0)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AppListHolder {
        return AppListHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_app, p0, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: AppListHolder, p1: Int) {
        p0.doBindData(list[p1])
        if(p1%2 == 0){
            p0.itemView.setBackgroundColor(Color.WHITE)
        }else{
            p0.itemView.setBackgroundColor(Color.GREEN)
        }

    }
}