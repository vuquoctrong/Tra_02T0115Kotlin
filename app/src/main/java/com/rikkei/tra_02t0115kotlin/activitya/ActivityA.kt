package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rikkei.tra_02t0115kotlin.activityb.ActivityB
import com.rikkei.tra_02t0115kotlin.util.getValue
import kotlinx.android.synthetic.main.activity_a.*


class ActivityA : AppCompatActivity(), ViewA {

    private var peresenterA: PresenterImpA? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rikkei.tra_02t0115kotlin.R.layout.activity_a)
        init()
    }

    private fun init() {
        peresenterA = PresenterImpA(this)
        btnSignUp.setOnClickListener { v ->
            peresenterA?.savePeople(
                etId.getValue(),
                etName.getValue(),
                etGender.getValue(),
                etAge.getValue().toInt(),
                etPlace.getValue()
            )
        }
        btnBack.setOnClickListener { v -> peresenterA?.saveTaskToSharedPrefs() }
    }

    override fun openB() {
        val intent = Intent(this, ActivityB::class.java)
        startActivity(intent)
    }
}
