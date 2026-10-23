package com.ron.ex06_2

data class Book(var isbn: String = "", var name: String = "", var price: Double = 0.0, var image: Int = R.drawable.ic_launcher_foreground) {
    override fun equals(other: Any?): Boolean {
        return this.isbn == (other as Book).isbn
    }

    override fun hashCode(): Int {
        return isbn.hashCode()
    }
}

