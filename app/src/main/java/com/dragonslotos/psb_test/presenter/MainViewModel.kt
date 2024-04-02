package com.dragonslotos.psb_test.presenter

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dragonslotos.psb_test.domain.models.Rate
import com.dragonslotos.psb_test.domain.usecases.GetRates
import com.dragonslotos.psb_test.domain.usecases.SharedPreferences
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainViewModel(getRates: GetRates, sharedPreferences: SharedPreferences):ViewModel() {
   //Load usecases
    private val getRates  = getRates
    private val sharedPreferences = sharedPreferences

    //Mutabble States
    val rateList: MutableStateFlow<List<Rate>> = MutableStateFlow(listOf())
    val rateLikeeList: MutableStateFlow<List<Rate>> = MutableStateFlow(listOf())
    val date: MutableStateFlow<Date> = MutableStateFlow(Date(12424))

    var isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var internetErrorr: MutableStateFlow<Boolean> = MutableStateFlow(false)


    private val subscription: Disposable = Observable.interval(30, TimeUnit.SECONDS)
        .subscribe { seconds ->
            loadRates()
        }

    init {
        loadRates()
    }

    //add or delete like rates
    public fun likeRates(code: String){
        sharedPreferences.changeRate(code)
        loadRates()
    }

    //load rates from cb
    public fun loadRates(){
        isRefreshing.value = true

        //run thread
        thread {
            //get liked rates
            val likee = sharedPreferences.getRates()
            //get daata
            val data = getRates.getRate()


            //check data
            if(data != null){

                //set time update
                date.value = data.getDateTime()

                //create list of rates
                val rates = data.getRates()
                //create list of liked rates
                var likeeRate = listOf<Rate>()

                //check like rates
                for (rate in rates){

                    //on like of rate and add to list
                    if(likee.contains(rate.charCode)){
                        rate.likee = true

                        likeeRate += rate
                    }
                }
                rateList.value = rates
                rateLikeeList.value = likeeRate
            }
           //if data null show error
           else{
               internetErrorr.value=true
            }
           isRefreshing.value = false;
        }
    }
}