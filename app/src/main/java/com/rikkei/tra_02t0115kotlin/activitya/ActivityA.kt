package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.rikkei.tra_02t0115kotlin.activityb.ActivityB
import com.rikkei.tra_02t0115kotlin.constant.Define
import com.rikkei.tra_02t0115kotlin.model.People
import kotlinx.android.synthetic.main.activity_a.*



class ActivityA : AppCompatActivity(),ViewA {


    private var people: People? = null
    private var id: String? = null
    private var name: String? = null
    private var gender: String? = null
    private var age: Int? = null
    private var place: String? = null
    private var peoples = mutableListOf<People>()

    private var peresenterA: PresenterImpA? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rikkei.tra_02t0115kotlin.R.layout.activity_a)
        init()
    }
    fun init(){
        peresenterA = PresenterImpA(this)
        btnSignUp.setOnClickListener { v ->  peresenterA?.savePeople()}
        btnBack.setOnClickListener { v ->  peresenterA?.openB()}
    }
    override fun savePeople() {
        id = toString(etId)
        name = toString(etName)
        gender = toString(etGender)
        age = toString(etAge).toInt()
        place = toString(etPlace)
        people = People(id!!, name!!, gender!!, age!!, place!! )
        peoples.add(people!!)

    }
    override fun openB() {
        peresenterA?.saveTaskToSharedPrefs(this)
        val intent = Intent(this, ActivityB::class.java)
        startActivity(intent)
    }

    override fun saveTaskToSharedPrefs(context: Context) {
        val appSharedPrefs: SharedPreferences? = PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
        val prefsEditor: SharedPreferences.Editor? = appSharedPrefs?.edit()
        val gson = Gson()
        val json: String? = gson.toJson(peoples)
        prefsEditor?.putString(Define::KEY_SHAREDPREFS.toString(),json)
        prefsEditor?.apply()
    }



    private fun toString(text: EditText) : String{
            return text.text.toString().trim()
    }

}
