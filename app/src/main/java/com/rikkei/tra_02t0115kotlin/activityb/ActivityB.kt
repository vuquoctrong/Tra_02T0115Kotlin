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
import kotlin.Comparator
import kotlin.collections.ArrayList
import android.support.v7.app.AlertDialog
import android.R
import android.widget.CheckBox
import android.widget.EditText
import android.view.LayoutInflater
import android.widget.Toast
import android.content.DialogInterface
import android.text.TextUtils
import android.view.View


class ActivityB : AppCompatActivity(), ViewB, PeopleAdapter.PeopleOnclickListener {


    private var peopleAdapter: PeopleAdapter? = null
    private var peoples = mutableListOf<People>()
    private var presenterImpB: PresenterImpB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rikkei.tra_02t0115kotlin.R.layout.activity_b)
        init()
    }

    private fun init() {

        presenterImpB = PresenterImpB(this)
        peoples = presenterImpB?.getTasksFromSharedPrefs(this)!!
        sortAgeList()
        peopleAdapter = PeopleAdapter(peoples, this)
        var layoutmanager = LinearLayoutManager(applicationContext)
        layoutmanager?.orientation = LinearLayoutManager.VERTICAL
        recyclerview?.layoutManager = layoutmanager
        recyclerview?.adapter = peopleAdapter

    }

    override fun getTasksFromSharedPrefs(context: Context): MutableList<People> {
        val appSharedPrefs: SharedPreferences? = PreferenceManager
            .getDefaultSharedPreferences(context.applicationContext)
        val gson = Gson()
        val json = appSharedPrefs?.getString(Define::KEY_SHAREDPREFS.toString(), "")
        peoples = gson.fromJson(json, object : TypeToken<ArrayList<People>>() {

        }.type)
        return peoples
    }

    private fun sortAgeList(): MutableList<People> {
        peoples.sortWith(Comparator { o1, o2 -> o1.age - o2.age })
        return peoples
    }

    override fun onClickItem(people: Int) {
        val colors = arrayOf<CharSequence>("Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose option")
        builder.setItems(colors) { dialog, which ->
            if (which == 0) {
                editPeople(people)
            } else {
                delete(people)
            }
        }
        builder.show()

    }

    private fun delete(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("People")
        builder.setMessage("Bạn có chắc muốn xóa")
        builder.setCancelable(false)
        builder.setPositiveButton("Ok") { _, _ ->
            peoples.removeAt(id)
            peopleAdapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton(
            "Canner"
        ) { dialogInterface, i -> dialogInterface.dismiss() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun editPeople(id: Int) {
        val inflater = layoutInflater
        val alertLayout =
            inflater.inflate(com.rikkei.tra_02t0115kotlin.R.layout.dialog_edit_people, null)
        val etIdEdit = alertLayout.findViewById(com.rikkei.tra_02t0115kotlin.R.id.etIdEdit) as EditText
        val etNameEdit = alertLayout.findViewById(com.rikkei.tra_02t0115kotlin.R.id.etNameEdit) as EditText
        val etGenderEdit = alertLayout.findViewById(com.rikkei.tra_02t0115kotlin.R.id.etGenderEdit) as EditText
        val etAgeEdit = alertLayout.findViewById(com.rikkei.tra_02t0115kotlin.R.id.etAgeEdit) as EditText
        val etPlaceEdit = alertLayout.findViewById(com.rikkei.tra_02t0115kotlin.R.id.etPlaceEdit) as EditText

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Edit")
            .setView(alertLayout)
            .setCancelable(false)
            .setNegativeButton(
                "Cancel"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Ok") { dialog, which ->

            }
        val dialog = alert.create()
        dialog.show()
        etIdEdit.setText(peoples[id].id)
        etNameEdit.setText(peoples[id].name)
        etGenderEdit.setText(peoples[id].gender)
        etAgeEdit.setText(peoples[id].age.toString())
        etPlaceEdit.setText(peoples[id].place)

        val alertDialog = alert.create()

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            upDatePeople(
                id,
                toString(etIdEdit),
                toString(etNameEdit),
                toString(etGenderEdit),
                toString(etAgeEdit).toInt(),
                toString(etPlaceEdit)
            )
        }

    }

    private fun upDatePeople(idEdit: Int, id: String, name: String, gender: String, age: Int, place: String) {
        peoples[idEdit].id = id
        peoples[idEdit].name = name
        peoples[idEdit].gender = gender
        peoples[idEdit].age = age
        peoples[idEdit].place = place
        peopleAdapter?.notifyDataSetChanged()
    }

    private fun toString(text: EditText): String {
        return text.text.toString().trim()
    }


}



