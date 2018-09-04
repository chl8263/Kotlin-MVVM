package com.example.gyun_home.seoulheartrescue.ui.fragment

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gyun_home.seoulheartrescue.BaseActivity
import com.example.gyun_home.seoulheartrescue.R
import com.example.gyun_home.seoulheartrescue.databinding.EducationBinding
import com.example.gyun_home.seoulheartrescue.viewmodel.EducationViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import android.os.AsyncTask
import android.util.Log
import com.example.gyun_home.seoulheartrescue.util.*
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class EducationFragment : Fragment() {

    var binding: EducationBinding? = null
    var model: EducationViewModel? = null

    var thisContext: Context? = null
    var calendarView: MaterialCalendarView? = null
    var sundayDecorator: SundayDecorator = SundayDecorator()
    var saturdayDecorator: SaturdayDecorator = SaturdayDecorator()
    var commondatDecorator: CommonDecorator = CommonDecorator()
    val toDayDecorator: ToDayDecorator = ToDayDecorator()
    val result = ArrayList<String>()

    companion object {
        fun newInstance(): EducationFragment {
            val args = Bundle()
            val fragment = EducationFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_education, container, false)

        if (activity != null && activity is BaseActivity) {
            model = (activity as BaseActivity).get_EducationViewModel()
            binding!!.model = model
        }

        var view = binding!!.root

        thisContext = container!!.context

        calendarView = view.findViewById(R.id.calendarView)

        calendarView!!.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {

                var year = date.year.toString()
                var month = (date.month + 1).toString()
                var dayy = date.day.toString()
                var date = year + "," + month + "," + dayy
                Log.e("select day", date)
                for (i in result) {
                    if (i == date) {
                        Log.e("!!!!", "찾았다!")
                    }
                }

            }

        })

        result.add("2018,09,18")
        result.add("2018,10,12")
        result.add("2018,10,19")
        result.add("2018,10,24")

        CheckPointCalender(result).executeOnExecutor(Executors.newSingleThreadExecutor())
        calendarView!!.addDecorators(commondatDecorator, sundayDecorator, saturdayDecorator, toDayDecorator)

        return view
    }

    private inner class CheckPointCalender(timeResult: ArrayList<String>) : AsyncTask<Void, Void, List<CalendarDay>>() {

        var timeResult = ArrayList<String>()

        init {
            this.timeResult = timeResult
        }

        override fun doInBackground(vararg p0: Void?): List<CalendarDay> {
            try {


            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            var calendar = Calendar.getInstance()
            var dates = ArrayList<CalendarDay>()

            for (i in timeResult.indices + 1) {
                var day = CalendarDay.from(calendar)
                var time = timeResult[i].split(",")
                var year = time[0].toInt()
                var month = time[1].toInt()
                var dayy = time[2].toInt()
                calendar.set(year, month - 1, dayy)
                if (i != 0) {
                    dates.add(day)
                }
            }

            return dates
        }

        override fun onPostExecute(result: List<CalendarDay>?) {
            super.onPostExecute(result)
            if (result != null) {
                for (i in result) {
                }
            }
            calendarView!!.addDecorator(EventDecorator(Color.GREEN, result!!, thisContext as Activity))
        }
    }

}