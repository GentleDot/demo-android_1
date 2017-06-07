package net.gentledot.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import net.gentledot.helloandroid.listeners.OnColorSelectedListener;
import net.gentledot.helloandroid.view.PaintBoardView;

public class PaintBoardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_COLOR = 101;

    PaintBoardView board;

    int mColor = 0xFF000000;
    int mSize = 2;

    boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_board);

        board = (PaintBoardView) findViewById(R.id.paintArea);

        Button colorSettingBtn = (Button) findViewById(R.id.setColorBtn);
        colorSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDialogActivity.listener = new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        mColor = color;
                        board.updatePaintProperty(mColor, mSize);
                    }
                };

                Intent intent = new Intent(getApplicationContext(), ColorDialogActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
            }
        });

        Button penSettingBtn = (Button) findViewById(R.id.setPenBtn);
        penSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        Button eraserBtn = (Button) findViewById(R.id.eraserBtn);
        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraserSelected = true;
                if(eraserSelected){

                } else {

                }
            }
        });

        Button undoBtn = (Button) findViewById(R.id.undoBtn);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.undo();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_COLOR){
            if(data != null){
                int color = data.getIntExtra("color", 0);
                board.setColor(color);
            }
        }
    }

    public int getChosenColor(){
        return mColor;
    }

    public int getPenThickness(){
        return mSize;
    }

}
