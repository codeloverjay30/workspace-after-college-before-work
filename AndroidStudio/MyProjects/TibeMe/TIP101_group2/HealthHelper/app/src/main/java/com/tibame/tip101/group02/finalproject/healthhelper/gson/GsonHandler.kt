package com.tibame.tip101.group02.finalproject.healthhelper.gson

import com.google.gson.Gson

val gson = Gson()

fun toJson(
   cls: Class<*>,
):String{
    return gson.toJson(cls)
}

fun fromJson(
    jsonString: String,
    cls: Class<*>,
):Any{
    return gson.fromJson(jsonString,cls::class.java)
}