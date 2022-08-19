package uz.bdm.dummyapiclient.api

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.bdm.dummyapiclient.App

object ApiService {

    var retrofit: Retrofit? = null

    val client = OkHttpClient.Builder()
        .addInterceptor(
            ChuckerInterceptor.Builder(App.app)
                .collector(ChuckerCollector(App.app))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .build()

    fun apiClient(): Api {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://dummyapi.io/data/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!.create(Api::class.java)
    }
}