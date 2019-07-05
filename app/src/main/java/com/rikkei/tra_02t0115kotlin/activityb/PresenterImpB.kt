package com.rikkei.tra_02t0115kotlin.activityb

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People
import com.rikkei.tra_02t0115kotlin.sharedpreferences.SharedPrefs
import kotlin.reflect.KClass

class PresenterImpB : PresenterB {
    private var peoples = ArrayList<People>()
    override fun getTasksFromSharedPrefs(context: Context): MutableList<People>? {
//        val appSharedPrefs: SharedPreferences? = PreferenceManager
//            .getDefaultSharedPreferences(context.applicationContext)
//        val gson = Gson()
//        val json = appSharedPrefs?.getString(Define.KEY_SHAREDPREFS, "")
//        return gson.fromJson(json, object : TypeToken<ArrayList<People>>() {
//        }.type)
       return SharedPrefs.getArrayObject(Define.KEY_SHAREDPREFS, listOf<People>().javaClass.kotlin)?.toMutableList()

    }
}