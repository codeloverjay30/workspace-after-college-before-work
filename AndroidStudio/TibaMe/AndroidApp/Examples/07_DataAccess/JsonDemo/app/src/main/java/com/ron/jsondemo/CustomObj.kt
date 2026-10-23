package com.ron.jsondemo

import java.util.*

class Book(var name: String, var price: Double, var author: String) {
    override fun toString(): String {
        return "name: $name, price: $price, author: $author"
    }
}

class Order(var id: String, var uid: String, var date: Date, var list: List<Book>) {
    override fun toString(): String {
        return "order ID: $id, UID: $uid, date: $date\nlist: $list"
    }
}