package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.*;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by Sang on 2017-06-01.
 */
public class ImageDisplayView extends View implements OnTouchListener {
    private static final String TAG = "ImageDisplayView";

    Paint paint;
    Matrix matrix;
    Canvas setCanvas;
    Bitmap iBitmap;
    Bitmap sourceBitmap;

    float displayWidth = 0.0F;
    float displayHeight = 0.0F;
    int displayCenterX = 0;
    int displayCenterY = 0;

    float sourceWidth = 0.0F;
    float sourceHeight = 0.0F;
    float bitmapCenterX = 0.0F;
    float bitmapCenterY = 0.0F;

    float scaleRatio = 0.0F;
    float totalScaleRatio = 0.0F;

    int lastX = 0;
    int lastY = 0;
    float startX = 0.0F;
    float startY = 0.0F;
    float oldDistance = 0.0F;

    boolean isScrolling = false;
    int oldPointerCount = 0;
    private static final float DISTANCE_THRESHOLD = 3.0F;

    private static final float MIN_SCALE_RATIO = 0.1F;
    private static final float MAX_SCALE_RATIO = 5.0F;


    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        matrix = new Matrix();

        lastX = -1;
        lastY = -1;

        setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // 화면에 보여지기 전 호출되는 메소드
        if (w > 0 && h > 0){
            newImage(w, h);
            redraw();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(iBitmap != null){
            canvas.drawBitmap(iBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) { // 터치 이벤트 처리
        final int action = event.getAction();

        int pointerCount = event.getPointerCount();
        Log.d(TAG, "Pointer Count = " + pointerCount);

        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(pointerCount == 1){
                    float curX = event.getX();
                    float curY = event.getY();

                    startX = curX;
                    startY = curY;
                } else if (pointerCount == 2){
                    oldDistance = 0.0F;
                    isScrolling = true;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(pointerCount == 1){
                    if(isScrolling){ // 더블 탭(double tap) 경우 무시
                        return true;
                    }

                    float curX = event.getX();
                    float curY = event.getY();

                    if(startX == 0.0F){
                        startX = curX;
                        startY = curY;
                        return true;
                    }

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if(oldPointerCount == 2){

                    } else {
                        Log.d(TAG, "ACTION_MOVE : " + offsetX + ", " + offsetY);

                        if (totalScaleRatio > 1.0F){
                            moveImage(-offsetX, -offsetY);
                        }

                        startX = curX;
                        startY = curY;
                    }

                } else if (pointerCount == 2){
                    float x1 = event.getX(0);
                    float y1 = event.getY(0);
                    float x2 = event.getX(1);
                    float y2 = event.getY(1);

                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = new Double(Math.sqrt(new Float(dx * dx + dy * dy).doubleValue())).floatValue();

                    float outScaleRatio = 0.0F;
                    if(oldDistance == 0.0F){
                        oldDistance = distance;
                        break;
                    }

                    if(distance > oldDistance){
                        if((distance - oldDistance) < DISTANCE_THRESHOLD){
                            return true;
                        }
                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);
                    } else if (distance < oldDistance){
                        if((oldDistance - distance) < DISTANCE_THRESHOLD){
                            return true;
                        }
                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }

                    if(outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO ){
                        Log.d(TAG, "유효하지 않은 배율 : " + outScaleRatio);
                    } else {
                        Log.d(TAG, "distance : " + distance + ", scaleRatio : " + outScaleRatio);
                        scaleImage(outScaleRatio);
                    }

                    oldDistance = distance;
                }
                oldPointerCount = pointerCount;
                break;

            case MotionEvent.ACTION_UP:
                if(pointerCount == 1){
                    float curX = event.getX();
                    float curY = event.getY();

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if(oldPointerCount == 2){

                    } else {
                        moveImage(-offsetX, -offsetY);
                    }
                } else {
                    isScrolling = false;
                }

                return true;
        }

        return true;
    }

    public void setImageData(Bitmap image){
        recycle();

        sourceBitmap = image;

        sourceWidth = sourceBitmap.getWidth();
        sourceHeight = sourceBitmap.getHeight();

        bitmapCenterX = sourceBitmap.getWidth() / 2;
        bitmapCenterY = sourceBitmap.getHeight() / 2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void redraw() {
        if(sourceBitmap == null){
            Log.d(TAG, "sourceBitmap is null in redraw().");
            return;
        }

        drawBackground(setCanvas);

        float originX = (displayWidth - (float) sourceBitmap.getWidth()) / 2.0F;
        float originY = (displayHeight - (float)sourceBitmap.getHeight()) / 2.0F;

        setCanvas.translate(originX, originY);
        setCanvas.drawBitmap(sourceBitmap, matrix, paint);
        setCanvas.translate(-originX, -originY);

        invalidate();
    }

    // 새로운 비트맵 이미지 생성
    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        iBitmap = img;
        setCanvas = canvas;

        displayWidth = (float) width;
        displayHeight = (float) height;

        displayCenterX = width/2;
        displayCenterY = height/2;

    }

    public void drawBackground(Canvas setCanvas) {
        if(setCanvas != null){
            setCanvas.drawColor(Color.BLACK);
        }
    }

    private void recycle() {
        if(sourceBitmap != null){
            sourceBitmap.recycle();
        }
    }

    // 이미지 이동
    private void moveImage(float offsetX, float offsetY) {
        Log.d(TAG, "moveImage() 호출됨 : " + offsetX + ", " + offsetY);
        matrix.postTranslate(offsetX, offsetY);
        redraw();
    }

    // 확대 및 축소
    private void scaleImage(float inScaleRatio) {
        Log.d(TAG, "scaleImage() 호출 : " + inScaleRatio);

        matrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);
        matrix.postRotate(0);

        totalScaleRatio = totalScaleRatio * inScaleRatio;

        redraw();
    }

}
