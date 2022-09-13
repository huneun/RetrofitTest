package com.example.retrofitconnection.utils

//문자열이 JsonObject OR JsonArray 형태에 따라
fun String?.isJsonObject():Boolean {
    return this?.startsWith("{" )== true && this.endsWith("}")
}

fun String?.isJsonArray(): Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}
