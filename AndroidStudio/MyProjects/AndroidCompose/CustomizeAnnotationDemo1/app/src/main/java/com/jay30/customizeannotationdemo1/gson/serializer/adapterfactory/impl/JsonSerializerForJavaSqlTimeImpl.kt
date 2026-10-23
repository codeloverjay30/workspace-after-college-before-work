package com.jay30.customizeannotationdemo1.gson.serializer.adapterfactory.impl

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

class JsonSerializerForJavaSqlTimeImpl(): JsonSerializerImpl< /* `AnnotationTarget.TYPE` must be in array of argument `allowedTargets`.*/ @JavaSqlTimeFormat String>() {

}