package net.gentledot.helloandroid.item;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-30.
 */
public class MonthItemView extends RelativeLayout {
    TextView monthText;

    public MonthItemView(Context context) {
        super(context);
        init(context);
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.month_item, this, true);

        monthText = (TextView) findViewById(R.id.monthText);
    }

    public void setDay(int day, int index){
        if (day != 0){
            monthText.setText(String.valueOf(day));
            if (index == 0 || index % 7 == 0){
                monthText.setTextColor(Color.RED);
            } else if ((index + 1) % 7 == 0){
                monthText.setTextColor(Color.CYAN);
            }
        } else {
            monthText.setText("");
        }
    }

}
