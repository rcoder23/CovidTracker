package fritprfojects.example.covid_19tracker

import android.graphics.Color
import android.text.Spannable
import android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

class SpannableData(langName:String, color: String, start:Int):SpannableString(langName){
    init {
        setSpan(
            ForegroundColorSpan(Color.parseColor(color)),
            start,
            langName.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}