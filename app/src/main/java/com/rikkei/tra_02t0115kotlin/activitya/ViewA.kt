package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context

interface ViewA {
    fun savePeople()
    fun openB()
    fun saveTaskToSharedPrefs(context: Context)
}