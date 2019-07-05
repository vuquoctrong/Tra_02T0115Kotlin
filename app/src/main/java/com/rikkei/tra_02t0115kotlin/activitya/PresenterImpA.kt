package com.rikkei.tra_02t0115kotlin.activitya

import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People
import com.rikkei.tra_02t0115kotlin.sharedpreferences.SharedPrefs

class PresenterImpA(var viewA: ViewA) : PresenterA {

    private var peoples = mutableListOf<People>()

    override fun savePeople(id: String, name: String, gender: String, age: Int, place: String) {
        peoples.add(People(id, name, gender, age, place))
    }

    override fun saveTaskToSharedPrefs() {
        SharedPrefs.putArrayObject(Define.KEY_SHAREDPREFS, peoples)
        viewA.openB()
    }

}