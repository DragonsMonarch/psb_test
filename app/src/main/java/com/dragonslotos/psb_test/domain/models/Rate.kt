package com.dragonslotos.psb_test.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rate(
    @Expose
    @SerializedName("ID")
    val id:String,
    @Expose
    @SerializedName("NumCode")
    val numCode:String,
    @Expose
    @SerializedName("CharCode")
    val charCode:String,
    @Expose
    @SerializedName("Nominal")
    val nominal:Int,
    @Expose
    @SerializedName("Name")
    val name:String,
    @Expose
    @SerializedName("Value")
    val value:Double,
    @Expose
    @SerializedName("Previous")
    val previous:Double,

    var likee: Boolean = false
)
