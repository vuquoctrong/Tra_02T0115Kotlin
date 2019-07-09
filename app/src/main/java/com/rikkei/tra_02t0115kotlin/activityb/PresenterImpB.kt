package com.rikkei.tra_02t0115kotlin.activityb

import com.google.gson.reflect.TypeToken
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People
import com.rikkei.tra_02t0115kotlin.sharedpreferences.SharedPrefs

class PresenterImpB : PresenterB {

    private var peoples = mutableListOf<People>()

    override fun upDatePeople(idEdit: Int, id: String, name: String, gender: String, age: Int, place: String) {
        peoples[idEdit].id = id
        peoples[idEdit].name = name
        peoples[idEdit].gender = gender
        peoples[idEdit].age = age
        peoples[idEdit].place = place
    }



    override fun getTasksFromSharedPrefs(): MutableList<People>? {
//        return SharedPrefs.instance.getArrayObject(Define.KEY_SHAREDPREFS, object : TypeToken<ArrayList<People>>() {
//
//        }.type as Class<MutableList<People>>)

//        return SharedPrefs.instance.getArrayObject(Define.KEY_SHAREDPREFS, object : TypeToken<MutableList<People>>() {
//
//        }.type as Class<MutableList<People>>)
//        return SharedPrefs.instance.getArrayObject(Define.KEY_SHAREDPREFS,
//            object : TypeToken<List<People>>() {}.type as Class<MutableList<People>>
//        )
        return SharedPrefs.instance.getArrayObject(Define.KEY_SHAREDPREFS, object : TypeToken<List<People>>(){})?.toMutableList()
    }
}