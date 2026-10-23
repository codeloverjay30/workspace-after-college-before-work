package com.jay30.customizeannotationdemo1.vo

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

data class User(
    val id: Int,
    @JavaSqlTimeFormat val createTime : String,
)
