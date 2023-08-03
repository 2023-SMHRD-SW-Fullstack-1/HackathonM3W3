package com.hjy.hackathon.mainfragment




import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hjy.hackathon.R
import com.hjy.hackathon.vo.ContentVO
import com.hjy.hackathon.adapter.ContentAdapter
import com.hjy.hackathon.adapter.DayViewContainer
import com.hjy.hackathon.adapter.MonthViewContainer
import com.hjy.hackathon.databinding.FragmentCalendarBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


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



        var content = binding.rvContent
        var data = ArrayList<ContentVO>()

        data.add(ContentVO("아침", "12,000",null))
        data.add(ContentVO("점심", "9,500",null))
        data.add(ContentVO("저녁", "32,000",null))

        var adapter: ContentAdapter = ContentAdapter(requireActivity(), R.layout.calendal_day_content, data)
        content.layoutManager = LinearLayoutManager(requireActivity()) // 목록형
        content.adapter = adapter


        binding.textView2.text = YearMonth.now().toString()
        val currentMonth = YearMonth.now()
        Log.d("YearMonth", currentMonth.toString());
        val startMonth = currentMonth.minusMonths(100)  // Adjust as needed
        val endMonth = currentMonth.plusMonths(100)  // Adjust as needed
//        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        val daysOfWeek = daysOfWeek(DayOfWeek.SUNDAY)
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)
        binding.calendarView.monthScrollListener = {
            binding.textView2.text = it.yearMonth.toString()

        }




        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.dayView.text = data.date.dayOfMonth.toString()
                if (data.position == DayPosition.MonthDate) {
                    container.dayView.setTextColor(Color.BLACK)
                    container.textView.setTextColor(Color.BLACK)
                } else {
                    container.dayView.setTextColor(Color.GRAY)
                    container.textView.setTextColor(Color.GRAY)
                }
            }
        }


        binding.calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                // Remember that the header is reused so this will be called for each month.
                // However, the first day of the week will not change so no need to bind
                // the same view every time it is reused.
                if (container.titlesContainer.tag == null) {
                    container.titlesContainer.tag = data.yearMonth
                    container.titlesContainer.children.map { it as TextView }
                        .forEachIndexed { index, textView ->
                            val dayOfWeek = daysOfWeek()[index]
                            val title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                            textView.text = title
                            // In the code above, we use the same `daysOfWeek` list
                            // that was created when we set up the calendar.
                            // However, we can also get the `daysOfWeek` list from the month data:
                            // val daysOfWeek = data.weekDays.first().map { it.date.dayOfWeek }
                            // Alternatively, you can get the value for this specific index:
                            // val dayOfWeek = data.weekDays.first()[index].date.dayOfWeek
                        }
                }
            }
        }

        //val titlesContainer = findViewById<ViewGroup>(R.id.titlesContainer)
        val titlesContainer = view.findViewById<ViewGroup>(R.id.titlesContainer)
        titlesContainer.children
            .map { it as TextView }
            .forEachIndexed { index, textView ->
                val dayOfWeek = daysOfWeek[index]
                val title = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                textView.text = title
                textView.setTextColor(Color.GRAY)

            }


        return view
    }




}