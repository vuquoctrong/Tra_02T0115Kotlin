package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context
import com.rikkei.tra_02t0115kotlin.model.People

interface PresenterA {

    fun saveTaskToSharedPrefs(context: Context,peoples: MutableList<People>)
}