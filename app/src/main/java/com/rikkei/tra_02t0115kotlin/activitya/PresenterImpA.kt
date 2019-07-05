package com.rikkei.tra_02t0115kotlin.activitya

import android.content.Context

class PresenterImpA(var viewA: ViewA) : PresenterA {


    override fun openB() {
        viewA?.openB()
    }

    override fun savePeople() {
        viewA?.savePeople()
    }
    override fun saveTaskToSharedPrefs(context: Context) {
        viewA?.saveTaskToSharedPrefs(context)

    }
}