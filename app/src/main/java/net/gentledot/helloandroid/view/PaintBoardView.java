package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.OutputStream;
import java.util.Stack;

/**
 * Created by Sang on 2017-06-03.
 */
public class PaintBoardView extends View{
    private static final String TAG = "PaintBoardView";
    private static final float TOUCH_TOLERANCE = 8;

    private final Path mPath = new Path();


    Stack undos = new Stack();
    public static int maxUndos = 10;

    public boolean changed = false;

    private Paint paint;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    private float prevX;
    private float prevY;

    private float mCurveEndX;
    private float mCurveEndY;

    private int mInvalidateExtraBorder = 10;



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

        if(w > 0 && h > 0){
            newImage(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mBitmap != null){
        canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        float curX = event.getX();
        float curY = event.getY();

        Rect rect = null;

        switch (action){
            case MotionEvent.ACTION_DOWN:
                saveUndo();

                rect = touchDown(event);

                if(rect != null){
                    invalidate(rect);
                }

                /*if(prevX != -1){
                    if (curX != prevX || curY != prevY){
                        mCanvas.drawLine(prevX, prevY, curX, curY, paint);
                    }
                }

                prevX = curX;
                prevY = curY;
                break;*/

                return true;
            case MotionEvent.ACTION_MOVE:
                rect = touchMove(event);

                if(rect != null){
                    invalidate(rect);
                }

                return true;

                /*if(prevX >= 0 || prevY >= 0){
                    mCanvas.drawLine(prevX, prevY, curX, curY, paint);
                }

                prevX = curX;
                prevY = curY;
                break;*/
            case MotionEvent.ACTION_UP:
                changed = true;

                rect = touchUp(event, false);

                if(rect != null){
                    invalidate(rect);
                }

                mPath.rewind();
                return true;

               /* prevX = -1;
                prevY = -1;

                break;*/
            default:
                break;

        }

        invalidate();
//        return true;
        return false;
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public void setLineWidth(float lineWidth){
        paint.setStrokeWidth(lineWidth);
    }

    public void clearUndo(){
        while (true){
            Bitmap prev = (Bitmap) undos.pop();
            if(prev == null){
                return;
            }
            prev.recycle();
        }
    }

    public void saveUndo(){
        if(mBitmap == null){
            return;
        }

        while(undos.size() >= maxUndos){
            Bitmap i = (Bitmap) undos.get(undos.size() - 1);
            i.recycle();
            undos.remove(i);
        }

        Bitmap img = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        undos.push(img);
        Log.d(TAG, "saveUndo() 호출됨.");
    }

    public void undo(){
        Bitmap prev = null;
        try{
            prev = (Bitmap) undos.pop();
        } catch (Exception e){
            Log.d(TAG, "Exception : " + e.getMessage());
        }

        if(prev != null){
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawBitmap(prev, 0, 0, paint);
            invalidate();

            prev.recycle();
        }

        Log.d(TAG, "undo() 호출됨.");
    }

    public void updatePaintProperty(int color, int size){
        paint.setColor(color);
        paint.setStrokeWidth(size);
    }

    public boolean Save(OutputStream outputStream){
        try {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            invalidate();

            return true;
        } catch (Exception e){
            Log.d(TAG, "Exception : " + e.getMessage());
            return false;
        }

    }

    private void init(Context context) {
        paint = new Paint();
        /*paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);*/

        paint.setAntiAlias(true);
        paint.setColor(0xFF000000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(2.0F);
        paint.setDither(true);

        prevX = -1;
        prevY = -1;

        Log.d(TAG, "paintBoard 초기화");
    }

    private void newImage(int width, int height){
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;

        mCanvas.drawColor(Color.WHITE);

        changed = false;
        invalidate();
    }

    private void setImage(Bitmap newImage){
        changed = false;

        setImageSize(newImage, newImage.getWidth(), newImage.getHeight());
    }

    private void setImageSize(Bitmap newImage, int width, int height) {
        if(mBitmap != null){
            width = width < mBitmap.getWidth() ? mBitmap.getWidth() : width;
            height = height < mBitmap.getHeight() ? mBitmap.getHeight() : height;
        }

        if (width < 1 || height < 1){
            return;
        }

        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.drawColor(Color.WHITE);

        if (newImage != null){
            canvas.setBitmap(newImage);
        }

        if(mBitmap != null){
            mBitmap.recycle();
            mCanvas.restore();
        }

        mBitmap = img;
        mCanvas = canvas;

        clearUndo();
    }

    private Rect touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        prevX = x;
        prevY = y;

        Rect mInvalidRect = new Rect();
        mPath.moveTo(x, y);

        final int border = mInvalidateExtraBorder;
        mInvalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);

        mCurveEndX = x;
        mCurveEndY = y;

        mCanvas.drawPath(mPath, paint);

        return mInvalidRect;
    }

    private Rect touchUp(MotionEvent event, boolean b) {
        Rect rect = processMove(event);

        return rect;
    }

    private Rect touchMove(MotionEvent event) {
        Rect rect = processMove(event);

        return rect;
    }

    private Rect processMove(MotionEvent event){
        final float x = event.getX();
        final float y = event.getY();

        final float dx = Math.abs(x - prevX);
        final float dy = Math.abs(y - prevY);

        Rect mInvalidRect = new Rect();

        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            final int border = mInvalidateExtraBorder;
            // 다시 그려질 영역으로 현재 이동한 좌표 추가
            mInvalidRect.set((int) mCurveEndX - border, (int) mCurveEndY - border, (int) mCurveEndX + border, (int) mCurveEndY + border);

            float cX = mCurveEndX = (x + prevX) / 2F;
            float cY = mCurveEndY = (y + prevY) / 2F;

            // Path 객체에 현재 좌표값을 곡선으로 추가
            mPath.quadTo(prevX, prevY, cX, cY);

            mInvalidRect.union((int) prevX - border, (int) prevY - border, (int) prevX + border, (int) prevY + border);
            mInvalidRect.union((int) cX - border, (int) cY - border, (int) cX + border, (int) cY + border);

            prevX = x;
            prevY = y;

            mCanvas.drawPath(mPath, paint);

        }
        return mInvalidRect;

    }


}

