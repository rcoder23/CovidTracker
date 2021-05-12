package fritprfojects.example.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var stateAdopter: StateAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header,list,false))
        fetchResult()
    }

    private fun fetchResult() {
        GlobalScope.launch {
            val respose: Response = withContext(Dispatchers.IO) { Clinet.api.execute() }
            if(respose.isSuccessful){

                val data: Response1? =Gson().fromJson(respose.body?.string(),Response1::class.java)
                launch(Dispatchers.Main){
                    if (data != null) {
                        bindCombinedData(data.statewise?.get(0))
                        bindStateWiseData(data.statewise!!.subList(0,data.statewise.size))
                    }
                }
            }
        }
    }

    private fun bindStateWiseData(subList: List<StatewiseItem?>) {

        stateAdopter=StateAdopter(subList)
        list.adapter=stateAdopter
    }

    private fun bindCombinedData(data: StatewiseItem?) {
        val lastUpdateTime= data?.lastupdatedtime
        val simpleDateFormat=SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdatetv.text="Last Updated\n ${getTimeAgo(simpleDateFormat.parse(lastUpdateTime))} "

        if (data != null) {
            Confirmedtv.text=data.confirmed
            recoveredtv.text=data.recovered
            activetv.text=data.active
            Deathtv.text= data.deaths
        }

    }
}

fun getTimeAgo(past: Date):String{
    val now=Date()
    val second=TimeUnit.MICROSECONDS.toSeconds(now.time-past.time)
    val minutes=TimeUnit.MICROSECONDS.toMinutes(now.time-past.time)
    val hours=TimeUnit.MICROSECONDS.toHours(now.time-past.time)
    return when{
        second <60->{
            "Few Second Ago"
        }
        minutes<60->{
            "$minutes minutes ago"
        }
        hours<24->
        {
            "$hours hours ${minutes % 60} min ago"
        }
        else ->{
            SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
        }
    }
}