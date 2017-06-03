package net.gentledot.helloandroid.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-06-03.
 */
public class CustomImageView extends View {
    Bitmap bitmap1;
    Bitmap memoryBitmap;

    public CustomImageView(Context context) {
        super(context);
        init(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(memoryBitmap, 0, 0, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        memoryBitmap = bitmap1.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas memoryCanvas = new Canvas();
        memoryCanvas.setBitmap(memoryBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        memoryCanvas.drawLine(100, 100, 400, 400, paint);

        memoryCanvas.drawBitmap(bitmap1, 0, 0, null);

    }

    private void init(Context context) {
       bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
    }


}
