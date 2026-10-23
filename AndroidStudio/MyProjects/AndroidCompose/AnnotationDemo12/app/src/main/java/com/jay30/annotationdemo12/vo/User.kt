package com.jay30.annotationdemo12.vo

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.annotation.Nullable

data class User(
    @SuppressLint("KotlinNullnessAnnotation") @NonNull val id:Int,
    @SuppressLint("KotlinNullnessAnnotation") @NonNull val name:String,
    @SuppressLint("KotlinNullnessAnnotation") @Nullable val description:String?,
){

}