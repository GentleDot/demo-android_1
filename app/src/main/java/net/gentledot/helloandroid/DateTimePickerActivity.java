package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import net.gentledot.helloandroid.picker.DateTimePicker;
import net.gentledot.helloandroid.picker.OnDateTimeChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimePickerActivity extends AppCompatActivity {
    TextView textView;
    DateTimePicker picker;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

      textView = (TextView) findViewById(R.id.currentTimeInfoText);
      picker = (DateTimePicker) findViewById(R.id.picker);

      // 현재 시간 텍스트뷰에 표시 (picker에서 받아오기)
      Calendar calendar = Calendar.getInstance();
      calendar.set(picker.getYear(), picker.getMonth(), picker.getDay(), picker.getHour(), picker.getMinute());
      textView.setText(sdf.format(calendar.getTime()));

      // 현재 시간 텍스트뷰에 표시
      picker.setOnDateTimeChangeListener(new OnDateTimeChangeListener() {
          @Override
          public void onDateTimeChange(DateTimePicker picker, int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
              Calendar cal = Calendar.getInstance();
              cal.set(year, monthOfYear, dayOfMonth, hour, minute);
              Date setDate = cal.getTime();
              String curTimeStr = sdf.format(setDate);
              textView.setText(curTimeStr);
          }
      });
    }
}
