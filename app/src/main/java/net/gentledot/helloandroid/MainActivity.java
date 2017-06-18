package net.gentledot.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rectangleBtn = (Button) findViewById(R.id.drawBtn);
        rectangleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DrawRectangleActivity.class));
            }
        });

        Button drawableBtn = (Button) findViewById(R.id.drawableVarBtn);
        drawableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DrawablesActivity.class));
            }
        });

        Button bitmapDemoBtn = (Button) findViewById(R.id.bitmapDemoBtn);
        bitmapDemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BitmapActivity.class));
            }
        });

        Button paintBoardBtn = (Button) findViewById(R.id.paintBoardBtn);
        paintBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PaintBoardActivity.class));
            }
        });

        Button surfaceViewBtn = (Button) findViewById(R.id.surfaceViewBtn);
        surfaceViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PaintBoardSurfaceActivity.class));
            }
        });

        Button coverFlowBtn = (Button) findViewById(R.id.coverFlowBtn);
        coverFlowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoverFlowActivity.class));
            }
        });
    }
}
