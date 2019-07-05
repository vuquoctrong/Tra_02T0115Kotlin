package com.rikkei.tra_02t0115kotlin.activityb

import android.os.Bundle

import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.LinearLayoutManager

import com.rikkei.tra_02t0115kotlin.adapter.PeopleAdapter

import com.rikkei.tra_02t0115kotlin.model.People

import kotlinx.android.synthetic.main.activity_b.*
import kotlin.Comparator

import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.EditText


class ActivityB : AppCompatActivity(), ViewB, PeopleAdapter.PeopleOnclickListener {

    private var peopleAdapter: PeopleAdapter? = null
    private var peoples = mutableListOf<People>()
    private var presenterImpB: PresenterImpB? = null

    private var people015 = mutableListOf<People>()
    private var people1639 = mutableListOf<People>()
    private var people4059 = mutableListOf<People>()
    private var people60100 = mutableListOf<People>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rikkei.tra_02t0115kotlin.R.layout.activity_b)
        init()
    }

    private fun init() {
        presenterImpB = PresenterImpB()
        peoples = presenterImpB?.getTasksFromSharedPrefs(this)!!
        showAgePeople()
        peopleAdapter = PeopleAdapter()
        peopleAdapter?.setPeopleOnclickListener(this)
        peopleAdapter?.setPeopleList(sortAgeList())
        var layoutmanager = LinearLayoutManager(applicationContext)
        layoutmanager?.orientation = LinearLayoutManager.VERTICAL
        recyclerview?.layoutManager = layoutmanager
        recyclerview?.adapter = peopleAdapter

        btnSort015.setOnClickListener { v -> peopleAdapter?.setPeopleList(people015) }
        btnSort1639.setOnClickListener { v -> peopleAdapter?.setPeopleList(people1639) }
        btnSort4059.setOnClickListener { v -> peopleAdapter?.setPeopleList(people4059) }
        btnSort6010.setOnClickListener { v -> peopleAdapter?.setPeopleList(people60100) }
        btnSortAllAge.setOnClickListener { v -> peopleAdapter?.setPeopleList(peoples) }
        btnSortName.setOnClickListener { v -> sortAlphabelList() }

    }

    private fun sortAgeList(): MutableList<People> {
        peoples.sortWith(Comparator { o1, o2 -> o1.age - o2.age })
        return peoples
    }

    private fun sortAlphabelList() {
        var sortedList = peoples.sortedWith(compareBy { it.name })
        peopleAdapter?.setPeopleList(sortedList as MutableList<People>)
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
            peopleAdapter?.setPeopleList(peoples)
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
            .setPositiveButton("Ok") { _, _ ->
                upDatePeople(
                    id,
                    toString(etIdEdit),
                    toString(etNameEdit),
                    toString(etGenderEdit),
                    toString(etAgeEdit).toInt(),
                    toString(etPlaceEdit)
                )

            }
        val dialog = alert.create()
        dialog.show()
        etIdEdit.setText(peoples[id].id)
        etNameEdit.setText(peoples[id].name)
        etGenderEdit.setText(peoples[id].gender)
        etAgeEdit.setText(peoples[id].age.toString())
        etPlaceEdit.setText(peoples[id].place)
    }

    private fun upDatePeople(idEdit: Int, id: String, name: String, gender: String, age: Int, place: String) {
        peoples[idEdit].id = id
        peoples[idEdit].name = name
        peoples[idEdit].gender = gender
        peoples[idEdit].age = age
        peoples[idEdit].place = place
        peopleAdapter?.setPeopleList(peoples)
    }

    private fun toString(text: EditText): String {
        return text.text.toString().trim()
    }

    private fun showAgePeople() {
        for (i in peoples) {
            when (i.age) {
                in 0..15 -> people015.add(i)
                in 16..39 -> people1639.add(i)
                in 40..59 -> people4059.add(i)
                else -> people60100.add(i)

            }
        }
    }

}



