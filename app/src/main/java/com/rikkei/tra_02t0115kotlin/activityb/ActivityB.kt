package com.rikkei.tra_02t0115kotlin.activityb

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager

import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.rikkei.tra_02t0115kotlin.adapter.PeopleAdapter
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People

import kotlinx.android.synthetic.main.activity_b.*



class ActivityB : AppCompatActivity(),ViewB {
    private var peopleAdapter: PeopleAdapter? = null
    private var peoples = mutableListOf<People>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rikkei.tra_02t0115kotlin.R.layout.activity_b)
        init()
    }
    fun init(){
        peoples = getTasksFromSharedPrefs(this)
        peopleAdapter = PeopleAdapter(peoples)
        var layoutmanager = LinearLayoutManager(applicationContext)
        layoutmanager?.orientation = LinearLayoutManager.VERTICAL
        recyclerview?.layoutManager = layoutmanager
        recyclerview?.adapter = peopleAdapter

    }

    fun getTasksFromSharedPrefs(context: Context): MutableList<People> {
        val appSharedPrefs: SharedPreferences? = PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
        val gson = Gson()
        val json = appSharedPrefs?.getString(Define::KEY_SHAREDPREFS.toString(), "")
        peoples = gson.fromJson(json, object : TypeToken<ArrayList<People>>() {

        }.type)
        return peoples
    }
}



