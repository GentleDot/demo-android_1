package net.gentledot.helloandroid;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import net.gentledot.helloandroid.gallery.CoverFlow;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CoverFlowActivity extends AppCompatActivity {

    // 간격 설정
    public static int spacing = -45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_flow);

        // 어댑터 설정
        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.coverflowArea);
        ImageAdapter coverImageAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(coverImageAdapter);

        // 커버플로우 속성 설정
        coverFlow.setSpacing(spacing);
        coverFlow.setSelection(2, true);
        coverFlow.setAnimationDuration(3000);

    }

    public class ImageAdapter extends BaseAdapter{

        int itemBackground;
        private Context mContext;
        private FileInputStream outstream;

        private Integer[] mImageIds = {R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04, R.drawable.item05};

        private ImageView[] mImages;

        public ImageAdapter(Context context) {
            mContext = context;
            this.mImages = new ImageView[mImageIds.length];
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return mImageIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView img = new ImageView(mContext);
            img.setImageResource(mImageIds[position]);
            img.setLayoutParams(new CoverFlow.LayoutParams(500, 280));
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
            drawable.setAntiAlias(true);

            return img;
        }

        public float getScale(boolean focused, int offset){
            return Math.max(0, 1.0F /(float) Math.pow(2, Math.abs(offset)));
        }
    }
}
