package net.gentledot.helloandroid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import net.gentledot.helloandroid.view.ImageDisplayView;

public class MultiTouchActivity extends AppCompatActivity {
    LinearLayout viewerContainer;

    ImageDisplayView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_touch);

        init();
    }

    private void init() {
        viewerContainer = (LinearLayout) findViewById(R.id.multiTouchViewerContainer);
        Bitmap sourceBitmap = loadImage();

        if(sourceBitmap != null){
            displayView = new ImageDisplayView(this);

            displayView.setImageData(sourceBitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.VERTICAL);
            viewerContainer.addView(displayView, params);
        }
    }

    private Bitmap loadImage() {
        Resources res = getResources();
        Bitmap setBitmap = BitmapFactory.decodeResource(res, R.drawable.image2);

        return setBitmap;
    }
}
