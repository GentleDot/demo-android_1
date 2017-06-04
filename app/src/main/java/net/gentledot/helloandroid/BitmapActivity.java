package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.gentledot.helloandroid.view.PaintBoardView;

public class BitmapActivity extends AppCompatActivity {
    PaintBoardView paintBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        paintBoard = (PaintBoardView) findViewById(R.id.paintArea);

    }
}
