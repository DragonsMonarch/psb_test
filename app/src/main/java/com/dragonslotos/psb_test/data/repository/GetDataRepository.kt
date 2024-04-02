package com.dragonslotos.psb_test.data.repository

import android.util.Log
import com.dragonslotos.psb_test.data.retrofit.Dataholder
import com.dragonslotos.psb_test.domain.models.Data
import com.dragonslotos.psb_test.domain.repository.DataRepository
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//get request for cb
class GetDataRepository  constructor(dataHolder: Dataholder): DataRepository {
    private val dataHolder = dataHolder
    override fun getRequest(): Data?{
        try{

            //get response
            val response= dataHolder.getRates().execute()
            Log.d("checker2", response.body().toString())
            return response.body()
            //write errors
        } catch (e: SocketTimeoutException){
            e.stackTrace
            return null
        }
        catch (e: UnknownHostException){
            e.stackTrace
            return null
        }
    }
}