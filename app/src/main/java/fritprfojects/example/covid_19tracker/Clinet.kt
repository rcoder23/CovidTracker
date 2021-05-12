package fritprfojects.example.covid_19tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Clinet {
    private val okHttpClinet=OkHttpClient()
    private val request=Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()

    val api= okHttpClinet.newCall(request)
}