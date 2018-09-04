package com.example.gyun_home.seoulheartrescue.ui.fragment

import android.app.Activity
import android.app.AlertDialog
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
import com.example.gyun_home.seoulheartrescue.databinding.ScheduleBinding
import com.example.gyun_home.seoulheartrescue.model.UserBaseModel
import com.example.gyun_home.seoulheartrescue.util.*
import com.example.gyun_home.seoulheartrescue.viewmodel.ScheduleViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ScheduleFragment : Fragment() {

    var binding: ScheduleBinding? = null
    var model: ScheduleViewModel? = null

    var thisContext: Context? = null
    var calendarView: MaterialCalendarView? = null
    var sundayDecorator: SundayDecorator = SundayDecorator()
    var saturdayDecorator: SaturdayDecorator = SaturdayDecorator()
    var commondatDecorator: CommonDecorator = CommonDecorator()
    val toDayDecorator: ToDayDecorator = ToDayDecorator()
    val result = ArrayList<String>()

    var userBaseModel : UserBaseModel? = UserBaseModel()

    var userId  = FirebaseAuth.getInstance().uid.toString()

    companion object {
        fun newInstance(): ScheduleFragment {
            val args = Bundle()
            val fragment = ScheduleFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)

        if (activity != null && activity is BaseActivity) {
            model = (activity as BaseActivity).get_ScheduleViewModel()
            binding!!.model = model
        }

        var view = binding!!.root

        thisContext = container!!.context

        calendarView = view.findViewById(R.id.calendarView)

        settingCalendar()

        return view
    }
    fun createScchduleDataBase(date : String){
        var map = HashMap<String, Any>()

        /*FirebaseFirestore.getInstance().collection("users").document(userId).get().addOnSuccessListener {
            if(it.exists()){
                userBaseModel = it.toObject(UserBaseModel::class.java)!!
                if(userBaseModel!!.schecule == null) {
                    userBaseModel!!.schecule = date
                }
                map["schecule"] = userBaseModel!!.schecule + "/" + date
                //userBaseModel!!.schecule = userBaseModel!!.schecule+"/"+date

                Log.e("스케쥴","있음")
            }else {
                map["schecule"] = date

                Log.e("스케쥴","없음")
            }
        }*/
        //userBaseModel.schecule = userBaseModel.schecule+"/"+date
        map["schecule"] = date

        FirebaseFirestore.getInstance().collection("users").document(userId).set(map).addOnSuccessListener {
            ToastMake.make(context!!,"데이터베이스 삽입 성공")
            Log.e("데이터", map.get("schecule").toString())
        }.addOnFailureListener {
            ToastMake.make(context!!,"데이터베이스 삽입 실패")
        }
    }

    fun settingCalendar(){
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

                        var alertDialog = AlertDialog.Builder(context)
                                .setTitle("교육신청")
                                .setMessage("교육을 신청 하시겠습니까?")
                                .setMessage("장소 : 광나루")
                                .setPositiveButton("신청",{dialogInterface, i ->
                                    createScchduleDataBase(date)
                                })
                                .setNegativeButton("취소",{dialogInterface, i ->  })
                        alertDialog.show()
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