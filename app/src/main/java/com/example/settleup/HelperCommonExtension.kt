package com.example.settleup

import com.google.gson.Gson

fun MutableList<String>.getjson(): String? {
     return Gson().toJson(this)
 }