package com.rikkei.tra_02t0115kotlin.activityb

import android.content.Context
import com.rikkei.tra_02t0115kotlin.model.People

interface ViewB {
    fun getTasksFromSharedPrefs(context: Context): MutableList<People>?
}