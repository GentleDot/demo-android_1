package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
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

        adapter = new MonthAdapter();

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

    class MonthAdapter extends BaseAdapter{


        MonthItem[] items;
        Calendar cal;
        int firstDay;
        int lastDay;
        int curYear;
        int curMonth;

        public MonthAdapter(){
            items = new MonthItem[ROW * COLUMN];
            cal = Calendar.getInstance();
            Date date = new Date();
            cal.setTime(date);

            reCalculrate();
            resetDayNumbers();
        }

        public void setPreviousMonth(){
            cal.add(Calendar.MONTH, -1);

            reCalculrate();
            resetDayNumbers();
        }

        public void setNextMonth(){
            cal.add(Calendar.MONTH, 1);

            reCalculrate();
            resetDayNumbers();
        }

        public int getCurYear(){
            return curYear;
        }

        public int getCurMonth(){
            return curMonth;
        }


        @Override
        public int getCount() {
            return ROW * COLUMN;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MonthItemView view = null;
            if(convertView == null){
                view = new MonthItemView(getApplicationContext());
            } else {
                view = (MonthItemView) convertView;
            }

            view.setDay(items[position].getDay(), position);

            return view;
        }

        private void reCalculrate() {
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            firstDay = getFirstDay(dayOfWeek);

            int firstDayOfWeek = cal.getFirstDayOfWeek();
            int startDay = getFirstDayOfWeek(firstDayOfWeek);

            curYear = cal.get(Calendar.YEAR);
            curMonth = cal.get(Calendar.MONTH) + 1;
            lastDay = getLastDay(curYear, curMonth);

        }

        private int getLastDay(int curYear, int curMonth) {
            int result = 0;

            switch (curMonth){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    result = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    result = 30;
                    break;
                default:
                    if(((curYear%4 == 0)&&(curYear%100 != 0)) ||(curYear%400 == 0)){
                        result = 29;
                    }else{
                        result = 28;
                    }
                    break;
            }

            return result;
        }

        private int getFirstDayOfWeek(int firstDayOfWeek) {
            int result = 0;

            return result;
        }

        private int getFirstDay(int dayOfWeek) {
            int result = 0;

            switch(dayOfWeek){
                case Calendar.SUNDAY:
                    result = 0;
                    break;
                case Calendar.MONDAY:
                    result = 1;
                    break;
                case Calendar.TUESDAY:
                    result = 2;
                    break;
                case Calendar.WEDNESDAY:
                    result = 3;
                    break;
                case Calendar.THURSDAY:
                    result = 4;
                    break;
                case Calendar.FRIDAY:
                    result = 5;
                    break;
                case Calendar.SATURDAY:
                    result = 6;
                    break;
                default:
                    result = 0;

            }

            return result;
        }

        private void resetDayNumbers() {
            for (int i = 0; i < 42; i++){
                int dayNumber = (i + 1) - firstDay;
                if (dayNumber < 1 || dayNumber > lastDay){
                    dayNumber = 0;
                }

                items[i] = new MonthItem(dayNumber);
            }
        }
    }



}
