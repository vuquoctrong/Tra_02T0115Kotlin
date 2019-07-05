package com.rikkei.tra_02t0115kotlin.util

import android.widget.EditText

fun EditText.getValue(): String {
    return this.text.toString().trim()
}