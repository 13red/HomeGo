package com.test.homego.data

import androidx.appcompat.app.AppCompatActivity
import com.test.homego.common.ConnectionCheck.checkInternet
import com.test.homego.data.model.AdsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

class DataConnection(val activity : AppCompatActivity) {

    fun getAds(callback: Callback<AdsData>) {
        if (checkInternet(activity)) {
            val service = RetrofitSingleton.getInstance().create(GetDataService::class.java)
            service.getAdds().enqueue(callback)
        }
    }

    private interface GetDataService {
        @GET("/properties")
        fun getAdds() : Call<AdsData>
    }
}

