package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context

interface PresenterA {

    fun savePeople(id: String, name: String, gender: String, age: Int, place: String)

    fun saveTaskToSharedPrefs()
}