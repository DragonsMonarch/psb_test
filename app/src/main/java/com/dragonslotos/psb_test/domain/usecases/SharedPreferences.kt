package com.dragonslotos.psb_test.domain.usecases

import android.content.Context
import android.util.Log

//shared prreferences for save likes rate
class SharedPreferences(context: Context) {

    //get shared preferences
    private val sharedPreferences = context.getSharedPreferences("Rates", Context.MODE_PRIVATE)

    // get rates from shared preferences
    public fun getRates(): Set<String>{
        return sharedPreferences.getStringSet("Rates", setOf())!!.toSet()
    }
    //add or delete data in shared preferences
    public fun changeRate(name: String){
        var setOfRates =  sharedPreferences.getStringSet("Rates", setOf())!!.toSet()

        //check contains of char code
        if (setOfRates.contains(name)){

            setOfRates -= name
        }
        else{
            setOfRates += name
        }

        //load in shared preferences
        with(sharedPreferences.edit()){
            putStringSet("Rates", setOfRates)
            apply()
        }
    }
}