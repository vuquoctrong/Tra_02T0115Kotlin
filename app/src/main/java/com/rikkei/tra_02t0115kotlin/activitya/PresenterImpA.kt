package com.rikkei.tra_02t0115kotlin.activitya

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People
import com.rikkei.tra_02t0115kotlin.sharedpreferences.SharedPrefs


class PresenterImpA(var viewA: ViewA) : PresenterA {

    private var peoples = mutableListOf<People>()
    init {
        peoples =  SharedPrefs.instance.getArrayObject(Define.KEY_SHAREDPREFS, object : TypeToken<List<People>>() {})
            ?.toMutableList()!!
    }

    override fun savePeople(id: String, name: String, gender: String, age: Int, place: String) {
        peoples.add(People(id, name, gender, age, place))

    }

    override fun saveTaskToSharedPrefs() {
        if (peoples.size != 0) {
            SharedPrefs.instance.putArrayObject(Define.KEY_SHAREDPREFS, peoples)
            viewA.openB()
        } else {
            viewA.errorEditPeople()
        }

    }

}