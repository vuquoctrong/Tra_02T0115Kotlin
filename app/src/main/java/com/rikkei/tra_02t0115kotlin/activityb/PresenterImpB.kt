package com.rikkei.tra_02t0115kotlin.activityb

import android.content.Context
import com.rikkei.tra_02t0115kotlin.model.People

class PresenterImpB(var viewB: ViewB?)  : PresenterB {
    override fun getTasksFromSharedPrefs(context: Context): MutableList<People>? {
       return viewB?.getTasksFromSharedPrefs(context)
    }
}