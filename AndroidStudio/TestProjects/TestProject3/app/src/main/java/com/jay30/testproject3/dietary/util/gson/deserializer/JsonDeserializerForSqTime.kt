package com.jay30.testproject3.dietary.util.gson.deserializer

import com.google.gson.JsonDeserializer
import com.google.gson.JsonParseException
import com.jay30.testproject3.dietary.util.gson.constant.SqlTimePattern
import java.sql.Time
import java.text.ParseException
import java.text.SimpleDateFormat

object JsonDeserializerForSqTime {
    @JvmField
	var timeDeserializer: JsonDeserializer<Time> = JsonDeserializer { json, typeOfT, context ->
        val timeFormat = SimpleDateFormat(SqlTimePattern.sqlTimePattern)
        try {
            return@JsonDeserializer Time(timeFormat.parse(json.asString).time)
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}
