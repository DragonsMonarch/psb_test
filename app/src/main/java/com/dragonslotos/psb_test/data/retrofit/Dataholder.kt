package com.dragonslotos.psb_test.data.retrofit
import com.dragonslotos.psb_test.domain.models.Data
import retrofit2.http.GET
import retrofit2.Call
interface Dataholder {
    @GET("https://www.cbr-xml-daily.ru/daily_json.js")
    public fun getRates(): Call<Data>
}