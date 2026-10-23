package com.ron.bookshopdbdemo_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookVM : ViewModel() {
    private val _bookSelectedState = MutableStateFlow(Book())
    val bookSelectedState: StateFlow<Book> = _bookSelectedState.asStateFlow()
    fun setSelectedBook(book: Book) {
        _bookSelectedState.value = book
    }

    // MutableStateFlow用來監控指定資料狀態，當資料一改變即可通知對應畫面更新
    // MutableStateFlow常與ViewModel搭配，可以讓UI元件在生命週期期間作出適當更新
    private val _booksState = MutableStateFlow(emptyList<Book>())
    val booksState: StateFlow<List<Book>> = _booksState.asStateFlow()

    init {
        // Launch a coroutine in the viewModelScope to call the suspend function
        viewModelScope.launch {
            _booksState.update {
                fetchBooks()
            }
        }
    }

    /**
     * 載入測試需要資料
     * @return 多本書資訊
     */
    private suspend fun fetchBooks(): List<Book> {
        // server URL
        val url = "${serverUrl}/BookServlet"
        val gson = Gson()

        // 發出POST請求，取得登入結果後回傳
        val result = httpPost(url, "")
        val collectionType = object : TypeToken<List<Book>>() {}.type
        val books = gson.fromJson<List<Book>>(result, collectionType)
        return books
    }
}