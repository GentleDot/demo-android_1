package net.gentledot.helloandroid.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.gentledot.helloandroid.R;
import org.w3c.dom.Text;

/**
 * Created by Sang on 2017-06-04.
 */
public class ColorItemView extends LinearLayout {
    private static final String TAG = "ColorItemView";

    TextView textView;

    public ColorItemView(Context context) {
        super(context);
        init(context);
    }

    public ColorItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public void setColor (int color){
//        Log.d(TAG, "receivedColorInt = " + color);
        textView.setText(" ");
        textView.setBackgroundColor(color);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.color_item, this, true);


        textView = (TextView) findViewById(R.id.colorSelectText);
    }

}
