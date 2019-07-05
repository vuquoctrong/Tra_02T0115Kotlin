package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People

class PresenterImpA(var viewA: ViewA) : PresenterA {

    override fun saveTaskToSharedPrefs(context: Context, peoples: MutableList<People>) {
        val appSharedPrefs: SharedPreferences? = PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
        val prefsEditor: SharedPreferences.Editor? = appSharedPrefs?.edit()
        val gson = Gson()
        val json: String? = gson.toJson(peoples)
        prefsEditor?.putString(Define::KEY_SHAREDPREFS.toString(), json)
        prefsEditor?.apply()
        viewA.openB()
    }

}