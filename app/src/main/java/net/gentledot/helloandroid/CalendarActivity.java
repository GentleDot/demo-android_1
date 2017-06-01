package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import net.gentledot.helloandroid.item.MonthAdapter;
import net.gentledot.helloandroid.item.MonthItem;
import net.gentledot.helloandroid.item.MonthItemView;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    private static final int ROW = 7;
    private static final int COLUMN = 6;

    TextView dateText;
    Button monthPrev;
    Button monthNext;
    GridView calendarView;
    MonthAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dateText = (TextView) findViewById(R.id.showDateText);
        monthPrev = (Button) findViewById(R.id.monthPrev);
        monthNext = (Button) findViewById(R.id.monthNext);
        calendarView = (GridView) findViewById(R.id.gridCalendar);

        adapter = new MonthAdapter(this);

        calendarView.setAdapter(adapter);
        calendarView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem item = (MonthItem) adapter.getItem(position);
                if (item.getDay() != 0){
                    Toast.makeText(CalendarActivity.this, "클릭한 날짜 : " + adapter.getCurYear() + "년 " + adapter.getCurMonth() + "월 " + item.getDay() + "일", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showSetDate();

        monthPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();

                showSetDate();
            }
        });

        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();

                showSetDate();
            }
        });

    }

    private void showSetDate() {
        dateText.setText(adapter.getCurYear() + "년 " + adapter.getCurMonth() + "월");
    }

}
