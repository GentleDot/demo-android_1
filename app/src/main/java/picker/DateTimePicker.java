package picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import net.gentledot.helloandroid.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sang on 2017-05-29.
 */
public class DateTimePicker extends LinearLayout {
    // 객체 생성.
    private DatePicker datePicker;
    private TimePicker timePicker;
    private CheckBox chkBox;
    private OnDateTimeChangeListener listener;

    // 생성자 (레이아웃 기본 2가지)
    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // XML 레이아웃을 인플레이션.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        chkBox = (CheckBox) findViewById(R.id.checkTimePicker);

        // 시간정보 참조.
        Calendar calendar = Calendar.getInstance();

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMin = calendar.get(Calendar.MINUTE);

        // 날짜 선택 위젯 초기화
        datePicker.init(curYear, curMonth, curDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(listener != null){ // 리스너로 이벤트 전달 ( getHour(), getMinute() 는 API 23 부터 지원하므로 이전 메소드 사용)
                    listener.onDateTimeChange(DateTimePicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        // 시간 선택 위젯 이벤트 처리.
        timePicker.setCurrentHour(curHour);
        timePicker.setCurrentMinute(curMin);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(listener != null){
                    listener.onDateTimeChange(DateTimePicker.this, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });

        // 체크박스 이벤트 처리.
        chkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                timePicker.setVisibility(chkBox.isChecked() ? View.VISIBLE : View.INVISIBLE);

            }
        });
    }

    // 새로운 리스너 객체 설정하는 메소드 정의.
    public void setOnDateTimeChangeListener(OnDateTimeChangeListener listener){
        this.listener = listener;
    }

    // 일시 변경하는 메소드
    public void updateDateTime(int year, int monthOfyear, int dayOfMonth, int hour, int minute){
        datePicker.updateDate(year, monthOfyear, dayOfMonth);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }

    // 일자 변경 메소드.
    public void updateDate(int year, int monthOfYear, int dayOfMonth){
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
    }

    // 24시간 형식 설정 메소드.
    public void setIs24HourView(final boolean is24Hour){
        timePicker.setIs24HourView(is24Hour);
    }

    // Getter
    public int getYear(){
        return datePicker.getYear();
    }

    public int getMonth(){
        return datePicker.getMonth();
    }

    public int getDay(){
        return datePicker.getDayOfMonth();
    }

    public int getHour(){
        return timePicker.getCurrentHour();
    }

    public int getMinute(){
        return timePicker.getCurrentMinute();
    }



}
