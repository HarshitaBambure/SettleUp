package com.example.settleup

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.widget.RelativeLayout
import com.example.settleup.helper.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


    fun Context.roundedCornersDrawable(
            borderWidth: Int =1, // border width in pixels
            borderColor: Int = Color.BLACK, // border color
            cornerRadius: Float = 25F, // corner radius in pixels
            bgColor: Int = Color.RED // view background color
    ): Drawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setStroke(borderWidth, borderColor)
            setColor(bgColor)
            // make it rounded corners
            this.cornerRadius = cornerRadius
        }
    }


fun MutableList<Int>.getjson(): String? {
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

fun String.getArray(): List<Int> {
    val listType = object : TypeToken<List<Int?>?>() {}.type
    val posts: List<Int> = Gson().fromJson(this, listType)
    return posts
}


