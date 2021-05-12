package fritprfojects.example.covid_19tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class StateAdopter(val list: List<StatewiseItem?>):BaseAdapter(){
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view=p1?:LayoutInflater.from(p2?.context).inflate(R.layout.item_list,p2,false)
        val item:StatewiseItem= list[p0]!!
        view.confimedTv.apply {
            text = SpannableData(
                "${item.confirmed}\n ↑ ${item.deltaconfirmed ?: "0"}",
                "#D32F2F", item.confirmed?.length ?: 0
            )
        }
        view.activeTv.text=SpannableData(
                "${item.active}\n ↑ ${item.deltaconfirmed ?: "0"}",
                "#1976D2", item.confirmed?.length ?: 0
            )

        view.recoveredTv.text=SpannableData (
                "${item.recovered}\n ↑ ${item.deltarecovered ?: "0"}",
                "#388E3C", item.recovered?.length ?: 0
            )

        view.deathTv.text= SpannableData(
                "${item.deaths}\n ↑ ${item.deltadeaths ?: "0"}",
                "#D32F2F",
                item.deaths?.length ?: 0
            )

        view.stateTv.text=item.state


            return view
        }

    override fun getItem(p0: Int)=list[p0]

    override fun getItemId(p0: Int)=p0.toLong()

    override fun getCount()=list.size
}

//    override fun getItem(p0: Int)=list[p0]

//    override fun getItemId(p0: Int)= p0.toLong()

//    override fun getCount()= list.size

