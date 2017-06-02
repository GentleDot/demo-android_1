package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.*;
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

//        canvas.drawRect(100, 100, 200, 200, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0F);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 100, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(128, 0, 0, 255);
        canvas.drawRect(120, 10, 210, 100, paint);

        DashPathEffect dashEffect = new DashPathEffect(new float[]{5, 5}, 1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0F);
        paint.setPathEffect(dashEffect);
        paint.setColor(Color.RED);
        canvas.drawRect(240, 10, 340, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40.0F);
        canvas.drawText("안녕하세요.", 20, 320, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0F);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(120.0F);
        canvas.drawText("Text (Stroke)", 20, 480, paint);

        Paint secondPaint = new Paint();

        secondPaint.setColor(Color.MAGENTA);
        canvas.drawCircle(50, 160, 40, secondPaint);

        secondPaint.setAntiAlias(true);
        secondPaint.setColor(Color.BLUE);
        canvas.drawCircle(160, 160, 40, secondPaint);

        canvas.clipRect(600, 620, 630, 650, Region.Op.REPLACE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(600, 620, 700, 720, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        
        if(action == MotionEvent.ACTION_DOWN){
            Toast.makeText(getContext(), "영역 누름", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    private void init(Context context) {
        paint = new Paint();

        paint.setColor(Color.RED);
    }
}
