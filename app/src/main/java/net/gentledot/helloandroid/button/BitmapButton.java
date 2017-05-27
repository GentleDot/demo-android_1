package net.gentledot.helloandroid.button;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-27.
 */
public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);

        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int action = event.getAction();

       switch (action){
           case MotionEvent.ACTION_DOWN:
               setBackgroundResource(R.drawable.bitmap_button_clicked);
               break;
           case MotionEvent.ACTION_UP:
               setBackgroundResource(R.drawable.bitmap_button_normal);
               break;
           default:
               setBackgroundResource(R.drawable.bitmap_button_normal);
       }

       invalidate(); // 다시 그리도록 요청

        return true;
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.bitmap_button_normal);

        float textsize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textsize); // 자바 소스에서는 px / bp 단위로만 지정 가능
        setTextColor(Color.WHITE);
    }


}
