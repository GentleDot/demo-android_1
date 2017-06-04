package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Sang on 2017-06-03.
 */
public class PaintBoardView extends View{
    Paint paint;
    Bitmap mBitmap;
    Canvas mCanvas;

    float prevX;
    float prevY;

    public PaintBoardView(Context context) {
        super(context);
        init(context);
    }

    public PaintBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        float curX = event.getX();
        float curY = event.getY();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                prevX = curX;
                prevY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                if(prevX >= 0 || prevY >= 0){
                    mCanvas.drawLine(prevX, prevY, curX, curY, paint);
                }

                prevX = curX;
                prevY = curY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;

        }

        invalidate();
        return true;
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public void setLineWidth(float lineWidth){
        paint.setStrokeWidth(lineWidth);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }
}

