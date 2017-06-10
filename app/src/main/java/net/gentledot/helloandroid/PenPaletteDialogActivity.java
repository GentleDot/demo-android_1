package net.gentledot.helloandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import net.gentledot.helloandroid.listeners.OnPenSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class PenPaletteDialogActivity extends AppCompatActivity {
    GridView grid;
    Button closeBtn;
    PenDataAdapter adapter;

    public static OnPenSelectedListener penListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pen_palette_dialog);

        setTitle("선 굵기 선택");

        grid = (GridView) findViewById(R.id.penGridView);
        closeBtn = (Button) findViewById(R.id.penCloseBtn);

        grid.setColumnWidth(14);
        grid.setBackgroundColor(Color.GRAY);
        grid.setVerticalSpacing(4);
        grid.setHorizontalSpacing(4);

        adapter = new PenDataAdapter(this);
        grid.setAdapter(adapter);
        grid.setNumColumns(adapter.getColumnCount());

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

class PenDataAdapter extends BaseAdapter{
    private static final String TAG = "PenDataAdapter";

    Context mContext;

    // 펜 굵기 지정
    public static final int[] PENS = new int[] {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 14, 16, 18, 20
    };

    int rowCount;
    int columnCount;

    public PenDataAdapter(Context context){
        super();

        mContext = context;

        rowCount = 3;
        columnCount = 5;
    }

    @Override
    public int getCount() {
        return rowCount * columnCount;
    }

    @Override
    public Object getItem(int position) {
        return PENS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // position 계산
        int rowIndex = position / rowCount;
        int columnIndex = position % rowCount;

        Log.d(TAG, "rowIndex = " + rowIndex + " // " + "columnIndex = " + columnIndex);

        // 펜 이미지 생성
        int areaWidth = 10;
        int areaHeight = 20;

        Bitmap penBitmap = Bitmap.createBitmap(areaWidth, areaHeight, Bitmap.Config.ARGB_8888);
        Canvas penCanvas = new Canvas();
        penCanvas.setBitmap(penBitmap);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        penCanvas.drawRect(0, 0, areaWidth, areaHeight, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float)PENS[position]);
        penCanvas.drawLine(0, areaHeight/2, areaWidth -1, areaHeight/2, mPaint);
        BitmapDrawable penDrawable = new BitmapDrawable(mContext.getResources(), penBitmap);

        // 버튼 생성
        Button aItem = new Button(mContext);
        aItem.setText(" ");
        aItem.setLayoutParams(new GridView.LayoutParams(
                GridView.LayoutParams.WRAP_CONTENT,
                GridView.LayoutParams.WRAP_CONTENT
        ));
        aItem.setPadding(4, 4, 4, 4);
        aItem.setBackgroundDrawable(penDrawable); // setBackground(Drawable) -- api16
        aItem.setHeight(120);
        aItem.setTag(PENS[position]);

        aItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PenPaletteDialogActivity.penListener != null){
                    PenPaletteDialogActivity.penListener.onPenSelected(((Integer)v.getTag()).intValue());
                }
                ((PenPaletteDialogActivity)mContext).finish();
            }
        });

        return aItem;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
