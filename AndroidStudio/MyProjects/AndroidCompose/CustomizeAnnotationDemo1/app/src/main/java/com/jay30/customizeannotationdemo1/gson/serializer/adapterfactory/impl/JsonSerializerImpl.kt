package com.jay30.customizeannotationdemo1.gson.serializer.adapterfactory.impl

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

open class JsonSerializerImpl<T>() :JsonSerializer<T>{
    override fun serialize(
        src: T,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}