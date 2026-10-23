package com.ron.bookscruddemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class BookViewModel : ViewModel() {
    private val _bookEditState = MutableStateFlow(Book())
    val bookEditState: StateFlow<Book> = _bookEditState.asStateFlow()
    fun setEditBook(book: Book) {
        _bookEditState.updateAndGet {
            book
        }
    }

    // MutableStateFlow用來監控指定資料狀態，當資料一改變即可通知對應畫面更新
    // MutableStateFlow常與ViewModel搭配，可以讓UI元件在生命週期期間作出適當更新
    private val _booksState = MutableStateFlow(emptyList<Book>())
    val booksState: StateFlow<List<Book>> = _booksState.asStateFlow()

    init {
        _booksState.update { fetchBooks() }
    }

    fun addItem(item: Book) {
        _booksState.update {
            val books = it.toMutableList()
            books.add(item)
            books
        }
    }

    fun removeItem(item: Book) {
        _booksState.update {
            val books = it.toMutableList()
            books.remove(item)
            books
        }
    }

    /**
     * 載入測試需要資料
     * @return 多本書資訊
     */
    private fun fetchBooks(): List<Book> {
        return listOf(
            Book("0001", "Android App", 600.0, R.drawable.android),
            Book("0002", "iOS App", 650.0, R.drawable.ios),
            Book("0003", "MySQL DB", 550.0, R.drawable.mysql),
            Book("0004", "Python", 500.0, R.drawable.python),
            Book("0005", "Kotlin", 520.0, R.drawable.kotlin),
            Book("0006", "MongoDB", 630.0, R.drawable.mongodb),
            Book("0007", "C/C++", 420.0, R.drawable.c_cplusplus),
            Book("0008", "C#", 480.0, R.drawable.csharp),
            Book("0009", "Swift", 540.0, R.drawable.swift),
            Book("0010", "Java", 520.0, R.drawable.java),
            Book("0011", "SQL Server", 620.0, R.drawable.sqlserver),
            Book("0012", "Oracle DB", 680.0, R.drawable.oracle),
            Book("0013", "HTML", 400.0, R.drawable.html),
            Book("0014", "Java Script", 470.0, R.drawable.javascript)
        )
    }
}