package com.hjy.hackathon.mainfragment



import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.hjy.hackathon.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {

    //바인딩
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val view = binding.root

        val calendarView: CalendarView = binding.calendarView


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth -> // 사용자가 선택한 날짜를 Calendar 객체로 변환
            val selectedDate: Calendar = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            // 데이터베이스에서 해당 날짜에 대한 정보를 가져옴
            // val eventData: String = getEventDataFromDatabase(selectedDate)

            // 달력 날짜에 해당 정보를 표시
            val message: String = ((selectedDate.get(Calendar.YEAR).toString() + "년 "+
                    (selectedDate.get(Calendar.MONTH) + 1)).toString() + "월 " +
                    selectedDate.get(Calendar.DAY_OF_MONTH)).toString() + "일"

            Log.d("message", message)

            binding.tvDate.visibility = View.VISIBLE
            binding.tvDate.text = message
        }



        return view
    }




}