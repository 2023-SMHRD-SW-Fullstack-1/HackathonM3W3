package com.hjy.hackathon.mainfragment




import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hjy.hackathon.R
import com.hjy.hackathon.vo.ContentVO
import com.hjy.hackathon.adapter.ContentAdapter
import com.hjy.hackathon.adapter.DayViewContainer
import com.hjy.hackathon.adapter.MonthViewContainer
import com.hjy.hackathon.databinding.FragmentCalendarBinding
import com.hjy.hackathon.vo.CalendarVO
import com.hjy.hackathon.vo.MemberVO
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import org.json.JSONArray
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


class CalendarFragment : Fragment() {

    //바인딩
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    lateinit var reqQueue: RequestQueue

    lateinit var map : HashMap<String, String>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val view = binding.root

        reqQueue = Volley.newRequestQueue(requireActivity())


        var spf: SharedPreferences =
            requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE)
        val strMember = spf.getString("member", "기본값")
        val memberVO = Gson().fromJson(strMember, MemberVO::class.java)
        val id = memberVO.mb_id


        var content = binding.rvContent
        var data = ArrayList<ContentVO>()

        data.add(ContentVO("아침", "12,000", null))
        data.add(ContentVO("점심", "9,500", null))
        data.add(ContentVO("저녁", "32,000", null))

        var adapter: ContentAdapter =
            ContentAdapter(requireActivity(), R.layout.calendal_day_content, data)
        content.layoutManager = LinearLayoutManager(requireActivity()) // 목록형
        content.adapter = adapter



        var date: String = YearMonth.now().toString()

        //년, 월 불러오기
        binding.textView2.text = YearMonth.now().toString()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        val daysOfWeek = daysOfWeek(DayOfWeek.SUNDAY)
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)
        binding.calendarView.monthScrollListener = {
            binding.textView2.text = it.yearMonth.year.toString() + "년 " + String.format(
                "%02d",
                it.yearMonth.month.value
            ) + "월"
        }





        var request = object : StringRequest(
            Request.Method.POST,
            "http://172.30.1.20:8888/calendar",
            { response ->
                Log.d("response", response)
                val result = JSONArray(response);
                map = HashMap<String, String>();
                var sum = 0;
                for (i in 0 until result.length()) {
                    map.put(result.getJSONObject(i).getString("board_at"), result.getJSONObject(i).getString("total_cost"));
                    sum += result.getJSONObject(i).getString("total_cost").toInt();
                }
                binding.tvTotal.text = sum.toString();

            },
            {
                    error->
                Log.d("error",error.toString())

            }

        ){override fun getParams() : MutableMap<String, String?>{
            val params:MutableMap<String, String?> = HashMap<String, String?>()

            val am: CalendarVO = CalendarVO(id, date)
            params.put("Andmember", Gson().toJson(am))
            return params}}
        reqQueue.add(request)




        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    if (container.titlesContainer.tag == null) {
                        container.titlesContainer.tag = data.yearMonth
                        container.titlesContainer.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                val dayOfWeek = daysOfWeek()[index]
                                val title =
                                    dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                textView.text = title
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





        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)


            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.dayView.text = data.date.dayOfMonth.toString()
                container.textView.text = map.getOrDefault((data.date.toString()),"");
                if (data.position == DayPosition.MonthDate) {
                    container.dayView.setTextColor(Color.BLACK)
                    if (container.dayView.text.toString() == "1") {
                        container.textView.setText("가나다")
                    }
                    container.textView.setTextColor(Color.rgb(247, 163, 192))
                    if (data.date.dayOfWeek == DayOfWeek.SUNDAY) {
                        container.dayView.setTextColor(Color.rgb(214, 44, 107))
                    } else if (data.date.dayOfWeek == DayOfWeek.SATURDAY) {
                        container.dayView.setTextColor(Color.rgb(25, 145, 251))
                    }

                } else {
                    container.dayView.setTextColor(Color.rgb(226, 226, 226))
                    container.textView.setTextColor(Color.rgb(226, 226, 226))
                }

//                var newDate = date+"-"+ container.dayView.text.toString()
//                Log.d("newDate", newDate)
//
//                var request = object : StringRequest(
//                    Request.Method.POST,
//                    "http://172.30.1.20:8888/calendar/dayMoney",
//                    { response ->
//                        Log.d("response", response.toString())
//                        var result = JSONArray(response)
//
//                    },
//                    {
//                            error ->
//                        Log.d("error", error.toString())
//                    }
//                ){override fun getParams() : MutableMap<String, String?>{
//                    val params:MutableMap<String, String?> = HashMap<String, String?>()
//
//                    params.put("newDate", newDate)
//                    return params}}
//                reqQueue.add(request)




                //날짜 클릭 이벤트
//                container.dayView.setOnClickListener {
//
//                    val clickedDate = data.date
//                    val day =
//                        "Clicked date: ${clickedDate.year}-${clickedDate.monthValue}-${clickedDate.dayOfMonth}"
//
//
//                    var request = object : StringRequest(
//                        Request.Method.POST,
//                        "http://172.30.1.20:8888/calendar",
//                        { response ->
//                            Log.d("response", response.toString())
//                            var result = JSONArray(response)
//
//
//                        },
//                        { error ->
//                            Log.d("error", error.toString())
//                        }
//
//                    ) {
//
//                        }
//                    }



                }
            }



           return view
        }


    }

