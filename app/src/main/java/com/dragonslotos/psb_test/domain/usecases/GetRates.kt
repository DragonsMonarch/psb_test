package com.dragonslotos.psb_test.domain.usecases

import com.dragonslotos.psb_test.domain.models.Data
import com.dragonslotos.psb_test.domain.repository.DataRepository

class GetRates(dataRepository: DataRepository) {
    private val dataRepository = dataRepository
    //send requset to repository
    fun getRate():Data?{
        //get request
        val request = dataRepository.getRequest()

        //check for null request
        if(request != null){
            return  request
        }
        else{
            return null
        }
    }
}