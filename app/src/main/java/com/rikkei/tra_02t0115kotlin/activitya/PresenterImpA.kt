package com.rikkei.tra_02t0115kotlin.activitya

class PresenterImpA(var viewA: ViewA) : PresenterA {
    override fun openB() {
        viewA?.openB()
    }

    override fun savePeople() {
        viewA?.savePeople()
    }
}