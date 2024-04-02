package com.dragonslotos.psb_test.domain.models

import android.os.Build
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


data class Data (
    @Expose
    @SerializedName("Date")
    val date:String,
    @Expose
    @SerializedName("PreviousDate")
    val previousDate:String,
    @Expose
    @SerializedName("PreviousURL")
    val previousURL:String,
    @Expose
    @SerializedName("Timestamp")
    val timestamp:String,
    @Expose
    @SerializedName("Valute")
    val valute:Valute){

    //functions for get in date format
    public fun getDateTime(): Date{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.parse(date.split("+")[0])
    }
    public fun getPreviousDateTime(): Date{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.parse(previousDate.split("+")[0])
    }
    public fun getTimestampDateTime(): Date{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return formatter.parse(timestamp.split("+")[0])
    }
    public fun getRates(): List<Rate>{
        return valute.getRates()
    }
}
data class Valute(
    @SerializedName("AUD")
    @Expose
    val aud: Rate,

    @SerializedName("AZN")
    @Expose
    val azn: Rate,

    @SerializedName("GBP")
    @Expose
    val gbp: Rate,

    @SerializedName("AMD")
    @Expose
    val amd: Rate,

    @SerializedName("BYN")
    @Expose
    val byn: Rate,

    @SerializedName("BGN")
    @Expose
    val bgn: Rate,

    @SerializedName("HUF")
    @Expose
    val huf: Rate,

    @SerializedName("VND")
    @Expose
    val vnd: Rate,

    @SerializedName("HKD")
    @Expose
    val hkd: Rate,

    @SerializedName("GEL")
    @Expose
    val gel: Rate,

    @SerializedName("BRL")
    @Expose
    val brl: Rate,

    @SerializedName("DKK")
    @Expose
    val dkk: Rate,

    @SerializedName("AED")
    @Expose
    val aed: Rate,

    @SerializedName("USD")
    @Expose
    val usd: Rate,

    @SerializedName("EUR")
    @Expose
    val eur: Rate,

    @SerializedName("EGP")
    @Expose
    val egp: Rate,

    @SerializedName("INR")
    @Expose
    val inr: Rate,

    @SerializedName("IDR")
    @Expose
    val idr: Rate,

    @SerializedName("KZT")
    @Expose
    val kzt: Rate,

    @SerializedName("CAD")
    @Expose
    val cad: Rate,

    @SerializedName("QAR")
    @Expose
    val qar: Rate,

    @SerializedName("KGS")
    @Expose
    val kgs: Rate,

    @SerializedName("CNY")
    @Expose
    val cny: Rate,

    @SerializedName("MDL")
    @Expose
    val mdl: Rate,

    @SerializedName("NZD")
    @Expose
    val nzd: Rate,

    @SerializedName("NOK")
    @Expose
    val nok: Rate,

    @SerializedName("PLN")
    @Expose
    val pln: Rate,

    @SerializedName("RON")
    @Expose
    val ron: Rate,

    @SerializedName("XDR")
    @Expose
    val xdr: Rate,

    @SerializedName("SGD")
    @Expose
    val sgd: Rate,

    @SerializedName("TJS")
    @Expose
    val tjs: Rate,

    @SerializedName("THB")
    @Expose
    val thb: Rate,

    @SerializedName("TRY")
    @Expose
    val trys: Rate,

    @SerializedName("TMT")
    @Expose
    val tmt: Rate,

    @SerializedName("UZS")
    @Expose
    val uzs: Rate,

    @SerializedName("UAH")
    @Expose
    val uah: Rate,

    @SerializedName("CZK")
    @Expose
    val czk: Rate,

    @SerializedName("SEK")
    @Expose
    val sek: Rate,

    @SerializedName("CHF")
    @Expose
    val chf: Rate,

    @SerializedName("RSD")
    @Expose
    val rsd: Rate,

    @SerializedName("ZAR")
    @Expose
    val zar: Rate,

    @SerializedName("KRW")
    @Expose
    val krw: Rate,

    @SerializedName("JPY")
    @Expose
    val jpy: Rate
){
    public fun getRates(): List<Rate>{
        return arrayListOf<Rate>(this.component1(), this.component2(), this.component3(), this.component4(),
            this.component5(), this.component6(), this.component7(), this.component8(),
            this.component9(), this.component10(), this.component11(), this.component12(),
            this.component13(), this.component14(), this.component15(), this.component16(),
            this.component17(), this.component18(), this.component19(), this.component20(),
            this.component21(), this.component22(), this.component23(), this.component24(),
            this.component25(), this.component26(), this.component27(), this.component28(),
            this.component29(), this.component30(), this.component31(), this.component32(),
            this.component33(), this.component34(), this.component35(), this.component36(),
            this.component37(), this.component38(), this.component39(), this.component40(),
            this.component41(), this.component42(), this.component43());
    }
}
