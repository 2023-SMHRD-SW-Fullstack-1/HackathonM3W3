package com.hjy.hackathon.adapter

import android.view.View
import android.widget.TextView
import com.hjy.hackathon.R
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    val dayView = view.findViewById<TextView>(R.id.calendarDayText)
    val textView = view.findViewById<TextView>(R.id.textView);

    // With ViewBinding
    // val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}