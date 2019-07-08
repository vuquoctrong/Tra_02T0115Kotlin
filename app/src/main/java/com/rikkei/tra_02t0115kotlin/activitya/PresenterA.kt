package com.rikkei.tra_02t0115kotlin.activitya

interface PresenterA {

    fun savePeople(id: String, name: String, gender: String, age: Int, place: String)

    fun saveTaskToSharedPrefs()
}