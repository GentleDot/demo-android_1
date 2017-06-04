package net.gentledot.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import net.gentledot.helloandroid.view.PaintBoardView;

public class PaintBoardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_COLOR = 101;

    PaintBoardView board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_board);

        board = (PaintBoardView) findViewById(R.id.paintArea);

        Button button = (Button) findViewById(R.id.setColorBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ColorDialogActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
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
}
