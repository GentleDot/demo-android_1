package net.gentledot.helloandroid.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-28.
 */
public class InfoItemView extends LinearLayout {
    TextView nameText;
    TextView telText;

    public InfoItemView(Context context) {
        super(context);
        init(context);
    }

    public InfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.info_item, this, true);

       nameText = (TextView) findViewById(R.id.nameTextView);
       telText = (TextView) findViewById(R.id.telTextView);
    }

    public void setName(String name){
        nameText.setText(name);
    }

    public void setTelNumber(String tel){
        telText.setText(tel);
    }
}
