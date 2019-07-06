package com.test.homego.data

import androidx.appcompat.app.AppCompatActivity
import com.test.homego.common.ConnectionCheck.checkPermissions
import com.test.homego.data.model.AdsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class DataConnection(val activity : AppCompatActivity) {

    fun getData() {
        if (checkPermissions(activity)) {
            val service = RetrofitSingleton.getInstance().create(GetDataService::class.java)
            service.getAdds().enqueue(object : Callback<AdsData> {
                override fun onFailure(call: Call<AdsData>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<AdsData>, response: Response<AdsData>) {
                    val addsData = response.body()
                    addsData?.items.toString()
                }

            })
        }
    }

    private interface GetDataService {
        @GET("/properties")
        fun getAdds() : Call<AdsData>
    }
}

