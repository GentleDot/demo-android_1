package net.gentledot.helloandroid.surfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.OutputStream;

/**
 * Created by Sang on 2017-06-14.
 */
public class PaintBoardSurface extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "PaintBoardSurface";

    SurfaceHolder mholder;

    private Paint mPaint;
    private Canvas mCanvas;

    private Bitmap mBitmap;

    int lastX;
    int lastY;

    public PaintBoardSurface(Context context) {
        super(context);

        init(context);
    }

    public PaintBoardSurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mholder = getHolder();
        mholder.addCallback(this);

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.RED);

        this.lastX = -1;
        this.lastY = -1;

        Log.d(TAG, "Initialized");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int width = getWidth();
        int height = getHeight();

        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawColor(Color.WHITE);

        mBitmap = img;
        mCanvas = canvas;

        repaintDraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (action){
            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;
                break;
            case MotionEvent.ACTION_DOWN:
                lastX = X;
                lastY = Y;

                if(lastX != 1){
                    if(X != lastX || Y != lastY){
                        mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(lastX != -1){
                    mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                }

                lastX = X;
                lastY = Y;

                break;
        }

        Log.d(TAG, "onTouchEvent: repaintCanvas()");

        // repaint the screen
        repaintDraw();
        return true;
    }

    public boolean save(OutputStream stream){
        try {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            invalidate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // draw this SurfaceView
    private void repaintDraw() {
        Canvas tempCanvas = null;
        try {
            tempCanvas = mholder.lockCanvas(null);
            super.draw(tempCanvas);
            tempCanvas.drawBitmap(mBitmap, 0, 0, null);
        } finally {
            if(tempCanvas != null){
                mholder.unlockCanvasAndPost(tempCanvas);
            }
        }
    }
}
