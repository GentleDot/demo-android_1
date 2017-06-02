package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Sang on 2017-06-02.
 */
public class CustomView extends View {
    Paint paint;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(100, 100, 200, 200, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        
        if(action == MotionEvent.ACTION_DOWN){
            Toast.makeText(getContext(), "사각형 누름.", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    private void init(Context context) {
        paint = new Paint();

        paint.setColor(Color.RED);
    }
}
