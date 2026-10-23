package com.tibame.tip101.exercise.exer6_02

data class Book(
    var isbn: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var image: Int = R.drawable.android
){
    override fun equals(other: Any?): Boolean {
        return this.isbn == (other as Book).isbn
    }

    override fun hashCode(): Int {
        return isbn.hashCode()
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(image.toString())
        stringBuilder.append(",")
        stringBuilder.append(name)
        stringBuilder.append(",")
        stringBuilder.append(isbn)
        stringBuilder.append(",")
        stringBuilder.append(price.toString())
        return stringBuilder.toString()
    }
}

