package net.gentledot.helloandroid.gallery;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Sang on 2017-06-17.
 */
public class CoverFlow extends Gallery { // 갤러리 클래스를 확장해 커버플로우 클래스로 정의
    private Camera camera = new Camera(); // 카메라 객체 선언과 생성

    // 회전 각도
    public static int maxRotationAngle = 55;

    // 최대 확대 수준
    public static int maxZoom = -60;

    private int centerPoint;

    public CoverFlow(Context context) {
        super(context);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // getChildTransformation 메소드 재정의(Override)
    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;

        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX); // Transformation 객체의 타입 지정

        if(childCenter == centerPoint){
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            // 중앙점으로부터의 거리에 따라 회전 각도 계산
            rotationAngle = (int) (((float) (centerPoint - childCenter) / childWidth) * maxRotationAngle);
            if (Math.abs(rotationAngle) > maxRotationAngle){
                rotationAngle = (rotationAngle < 0) ? -maxRotationAngle : maxRotationAngle;
            }
            // 새로 정의한 이미지 변환 메소드 호출
            transformImageBitmap((ImageView) child, t, rotationAngle);
        }

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // 뷰가 화면에 보이기 전에 중앙점 좌표 확인
        centerPoint = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // 생성자 호출 시 setStaticTransformationEnabled() 호출
    private void init() {
        this.setStaticTransformationsEnabled(true);
    }

    private int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }


    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
        camera.save();

        final Matrix imageMatrix = t.getMatrix();
        final int imageWidth = child.getLayoutParams().width;
        final int imageHeight = child.getLayoutParams().height;
        final int rotation = Math.abs(rotationAngle);

        camera.translate(0.0F, 0.0F, 100.0F);

        if(rotation < maxRotationAngle){
            float zoomAmount = (float) (maxZoom + (rotation * 1.5));
            camera.translate(0.0F, 0.0F, zoomAmount);
        }

        camera.rotateY(rotationAngle);
        camera.getMatrix(imageMatrix);

        imageMatrix.preTranslate(-(imageWidth/2), -(imageHeight/2));
        imageMatrix.postTranslate((imageWidth/2), (imageHeight/2));

        camera.restore();
    }

    private int getCenterOfCoverflow() { // 중앙점 좌표 계산
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }
}
