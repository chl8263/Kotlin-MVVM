package com.example.gyun_home.seoulheartrescue.util

import android.app.Activity
import android.graphics.drawable.Drawable
import com.example.gyun_home.seoulheartrescue.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator : DayViewDecorator{

    var drawable : Drawable?= null
    var color : Int? = null
    var dates : HashSet<CalendarDay>? = null


    constructor(color : Int , dates : Collection<CalendarDay> , context : Activity){
        drawable = context.resources.getDrawable(R.drawable.more)
        this.color = color
        this.dates = HashSet<CalendarDay>(dates)
    }
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates!!.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.setSelectionDrawable(drawable!!)
    }

}