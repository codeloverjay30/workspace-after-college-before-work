package com.ron.bookshopdbdemo_android

data class Book(
    var isbn: String = "",
    var name: String = "",
    var price: Double = 0.0,
    var author: String = ""
) {
    override fun equals(other: Any?): Boolean {
        return this.isbn == (other as Book).isbn
    }

    override fun hashCode(): Int {
        return isbn.hashCode()
    }
}

