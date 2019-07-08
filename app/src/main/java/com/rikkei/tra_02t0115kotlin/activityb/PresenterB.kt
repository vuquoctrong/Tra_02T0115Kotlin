package com.rikkei.tra_02t0115kotlin.activityb

import com.rikkei.tra_02t0115kotlin.model.People

interface PresenterB {
    fun getTasksFromSharedPrefs(): MutableList<People>?
    fun upDatePeople(idEdit: Int, id: String, name: String, gender: String, age: Int, place: String)
}