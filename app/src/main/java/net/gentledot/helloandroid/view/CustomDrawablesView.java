package net.gentledot.helloandroid.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-06-03.
 */
public class CustomDrawablesView extends View {
    Paint paint;

    int deviceWidth;
    int deviceHeight;

    ShapeDrawable shapeDrawable;

    public CustomDrawablesView(Context context) {
        super(context);
        init(context);
    }

    public CustomDrawablesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        shapeDrawable.draw(canvas);

        Paint pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setColor(Color.YELLOW);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(16.0F);
        pathPaint.setStrokeCap(Paint.Cap.BUTT);
        pathPaint.setStrokeJoin(Paint.Join.MITER);

        Path path = new Path();
        path.moveTo(20, 20);
        path.lineTo(120, 20);
        path.lineTo(160, 90);
        path.lineTo(180, 80);
        path.lineTo(200, 120);

        canvas.drawPath(path, pathPaint);

        pathPaint.setColor(Color.argb(128, 255, 255, 255));
        pathPaint.setStrokeCap(Paint.Cap.ROUND);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        path.offset(30, 120);
        canvas.drawPath(path, pathPaint);

        pathPaint.setColor(Color.CYAN);
        pathPaint.setStrokeCap(Paint.Cap.SQUARE);
        pathPaint.setStrokeJoin(Paint.Join.BEVEL);
        path.offset(30, 120);
        canvas.drawPath(path, pathPaint);

    }



    private void init(Context context) {
        paint = new Paint();

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        /*deviceWidth = display.getWidth();
        deviceHeight = display.getHeight();*/
        Point pt = new Point();
        display.getSize(pt);

        deviceWidth = pt.x;
        deviceHeight = pt.y;

        Resources res = getResources();
        int blackColor = res.getColor(R.color.colorBlack1);
//        int grayColor = res.getColor(R.color.colorGray1, null);


        shapeDrawable = new ShapeDrawable();
        RectShape rect = new RectShape();
        rect.resize(deviceWidth, deviceHeight / 2.0F);
        shapeDrawable.setShape(rect);
        shapeDrawable.setBounds(0, 0, deviceWidth, deviceHeight);

        LinearGradient gradient = new LinearGradient(0, 0, 0, deviceHeight, Color.BLUE, Color.YELLOW, Shader.TileMode.CLAMP);
        Paint setPaint = shapeDrawable.getPaint();
        setPaint.setShader(gradient);

    }
}
