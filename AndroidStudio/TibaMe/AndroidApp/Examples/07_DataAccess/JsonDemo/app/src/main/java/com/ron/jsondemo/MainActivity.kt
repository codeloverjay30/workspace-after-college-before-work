package com.ron.jsondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ron.jsondemo.ui.theme.JsonDemoTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = { text = demoObjectToJson() }
        ) {
            Text(text = stringResource(id = R.string.object_to_json))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = { text = demoListToJson() }
        ) {
            Text(text = stringResource(id = R.string.list_to_json))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = { text = demoMultiTypes() }
        ) {
            Text(text = stringResource(id = R.string.multiple_type))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = { text = demoObjectListToJson() }
        ) {
            Text(text = stringResource(id = R.string.object_list_to_json))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text)
    }
}

// 測試資料
private val book1: Book = Book("Java", 500.0, "John")
private val book2: Book = Book("Android", 600.0, "Allen")
private val list = listOf(book1, book2)
private val order = Order("111", "mary", Date(), list)
x
fun demoObjectToJson(): String {
    val sb = StringBuilder()
    val gson = Gson()
    // Object to JSON
    val jsonStr = gson.toJson(book1)
    sb.append("Object to JSON:\n $jsonStr\n")
    // JSON to Object
    val myBook = gson.fromJson(jsonStr, Book::class.java)
    sb.append("JSON to Object:\n $myBook\n")
    return sb.toString()
}

fun demoListToJson(): String {
    val sb = StringBuilder()
    val gson = Gson()
    // List to JSON
    val jsonStr = gson.toJson(list)
    sb.append("List to JSON:\n $jsonStr\n")
    // JSON to List
    /* TypeToken represents a generic type. TypeToken is an abstract class but with
     * no abstract methods, thus we don't have to override any method in TypeToken.	*/
    val collectionType = object : TypeToken<List<Book>>() {}.type
    val myBookList = gson.fromJson<List<Book>>(jsonStr, collectionType)
    sb.append("JSON to List:\n $myBookList\n")
    return sb.toString()
}

fun demoMultiTypes(): String {
    val sb = StringBuilder()
    val gson = Gson()
    var jsonObject = JsonObject()
    // Multi-types to JSON
    jsonObject.addProperty("uid", "mary")
    jsonObject.addProperty("list", gson.toJson(list))
    val jsonStr = jsonObject.toString()
    sb.append("Multi-types to JSON:\n $jsonStr\n")
    // JSON to Multi-types
    jsonObject = gson.fromJson(jsonStr, JsonObject::class.java)
    val uid = jsonObject.get("uid").asString
    val listJsonStr = jsonObject.get("list").asString
    val collectionType = object : TypeToken<List<Book>>() {}.type
    val myBookList = gson.fromJson<List<Book>>(listJsonStr, collectionType)
    sb.append("JSON to Multi-types:\n UID: $uid\n $myBookList\n")
    return sb.toString()
}

fun demoObjectListToJson(): String {
    val sb = StringBuilder()
    // Configures Gson to serialize Date objects according to the pattern provided.
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
    // Object (with List) to JSON
    val jsonStr = gson.toJson(order)
    sb.append("Object (with List) to JSON:\n $jsonStr\n")
    // JSON to Object (with List)
    val myOrder = gson.fromJson(jsonStr, Order::class.java)
    sb.append("JSON to Object (with List):\n $myOrder\n")
    return sb.toString()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JsonDemoTheme {
        Main()
    }
}