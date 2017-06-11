package net.gentledot.helloandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.gentledot.helloandroid.listeners.OnColorSelectedListener;
import net.gentledot.helloandroid.listeners.OnPenSelectedListener;
import net.gentledot.helloandroid.view.PaintBoardView;

public class PaintBoardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_COLOR = 101;

    PaintBoardView board;
    Button colorSettingBtn;
    Button penSettingBtn;
    Button eraserBtn;
    Button undoBtn;
    Button curColorInfo;
    TextView curSizeInfo;

    LinearLayout infoArea;

    int oldColor = 0;
    int oldSize = 0;

    int mColor = 0xFF000000;
    int mSize = 2;

    boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_board);

        board = (PaintBoardView) findViewById(R.id.paintArea);

        colorSettingBtn = (Button) findViewById(R.id.setColorBtn);
        colorSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ColorDialogActivity.listener = new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        *//*mColor = color;
                        board.updatePaintProperty(mColor, mSize);*//*
                    }
                };*/

                Intent intent = new Intent(getApplicationContext(), ColorDialogActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
            }
        });

        penSettingBtn = (Button) findViewById(R.id.setPenBtn);
        penSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PenPaletteDialogActivity.penListener = new OnPenSelectedListener() {
                    @Override
                    public void onPenSelected(int penSize) {
                        mSize = penSize;
                        board.updatePaintProperty(mColor, mSize);
                        displayPaintInfo();
                        Log.d("currentPenSize", "penSize : " + mSize);
                    }
                };

                Intent intent = new Intent(getApplicationContext(), PenPaletteDialogActivity.class);
                startActivity(intent);
            }
        });

        eraserBtn = (Button) findViewById(R.id.eraserBtn);
        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraserSelected = !eraserSelected;
                if(eraserSelected){
                    colorSettingBtn.setEnabled(false);
                    penSettingBtn.setEnabled(false);
                    undoBtn.setEnabled(false);

                    colorSettingBtn.invalidate();
                    penSettingBtn.invalidate();
                    undoBtn.invalidate();

                    oldColor = mColor;
                    oldSize = mSize;

                    mColor = Color.WHITE;
                    mSize = mSize * 2;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintInfo();
                } else {
                    colorSettingBtn.setEnabled(true);
                    penSettingBtn.setEnabled(true);
                    undoBtn.setEnabled(true);

                    colorSettingBtn.invalidate();
                    penSettingBtn.invalidate();
                    undoBtn.invalidate();

                    mColor = oldColor;
                    mSize = oldSize;

                    board.updatePaintProperty(mColor, mSize);
                    displayPaintInfo();
                }
            }
        });

        undoBtn = (Button) findViewById(R.id.undoBtn);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.undo();
            }
        });

        infoArea = (LinearLayout) findViewById(R.id.showPaintInfoArea);

        curColorInfo = (Button) findViewById(R.id.curColorShowBtn);
        curColorInfo.setText(" ");
        curColorInfo.setBackgroundColor(mColor);

        curSizeInfo = (TextView) findViewById(R.id.curSizeShowText);
        curSizeInfo.setText("Size : " + mSize);
        curSizeInfo.setTextSize(10.0F);
        curSizeInfo.setTextColor(Color.BLACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_COLOR){
            if(data != null){
                int color = data.getIntExtra("color", 0);
                mColor = color;
                board.setColor(mColor);
                displayPaintInfo();
                Log.d("currentColor", "color : " + mColor);
            }
        }
    }

    public int getChosenColor(){
        return mColor;
    }

    public int getPenThickness(){
        return mSize;
    }

    private void displayPaintInfo() {
        curColorInfo.setBackgroundColor(mColor);
        curSizeInfo.setText("Size : " + mSize);

        infoArea.invalidate();
    }



}
