package com.jay30.customnavtypedemo

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class SearchRoute(val parameters: SearchParameters)

@Serializable
@Parcelize
data class SearchParameters(
    val searchQuery: String,
    val filters: List<String>
)

@Composable
fun CustomNavTypeDemoFrame(){

}