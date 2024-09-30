package com.example.test.extension

fun String.removeSpace(): String{
    return this.trim().replace(" ", "")
}
fun String.removeMimeType(): String{
    return this.replace(".png", "")
}

