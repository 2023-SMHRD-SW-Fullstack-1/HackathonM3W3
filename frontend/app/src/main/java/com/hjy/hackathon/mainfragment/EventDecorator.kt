import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.widget.CalendarView
import androidx.core.content.ContextCompat
import com.hjy.hackathon.R
import java.util.*

class EventDecorator(private val context: Context, private val markedDates: Set<Long>) : View(context) {

    private val paint: Paint = Paint()
    private val calendar: Calendar = Calendar.getInstance()

    init {
        paint.color = ContextCompat.getColor(context, R.color.red) // 점의 색상을 설정합니다.
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val calendarView = parent as CalendarView
        val cellWidth = calendarView.width / 7
        val cellHeight = calendarView.height / 6

        for (dateInMillis in markedDates) {
            calendar.timeInMillis = dateInMillis
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            // 날짜의 위치를 계산하여 캔버스에 원을 그립니다.
            val x = (cellWidth * (dayOfMonth - 1)) + cellWidth / 2
            val y = (cellHeight * month) + cellHeight / 2
            canvas.drawCircle(x.toFloat(), y.toFloat(), 8f, paint) // 점의 크기를 설정합니다.
        }
    }
}
