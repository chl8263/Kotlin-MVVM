package com.example.gyun_home.seoulheartrescue.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.text.SimpleDateFormat
import java.util.*

class  ToDayDecorator : DayViewDecorator{

    var date : CalendarDay? = null

    constructor(){
        date = CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {

        return date != null && day!! == date
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(StyleSpan(Typeface.BOLD))
        view!!.addSpan(RelativeSizeSpan(1.4f))
        view!!.addSpan(ForegroundColorSpan(Color.GREEN))
    }


}