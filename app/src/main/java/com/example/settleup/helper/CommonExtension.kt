package com.example.settleup

import android.content.Context
import com.example.settleup.helper.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




fun MutableList<String>.getjson(): String? {
     return Gson().toJson(this)
 }

//this method is for save preferances
fun Context.savePreferances(key: String, value: String){
    val sharedPref = this.getSharedPreferences(Constants.PREF_SETTLE_UP, Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString(key, value)
    editor.apply()
}

//this method is for get preferances
fun Context.getPreferances(key: String): String? {
    val sharedPref = this.getSharedPreferences(Constants.PREF_SETTLE_UP, Context.MODE_PRIVATE)
    return sharedPref.getString(key, "")
}

fun String.getArray(): List<String> {
    val listType = object : TypeToken<List<String?>?>() {}.type
    val posts: List<String> = Gson().fromJson(this, listType)
    return posts
}


