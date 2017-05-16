package net.gentledot.helloandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class InflationActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation);

        container = (LinearLayout) findViewById(R.id.contentBox);

        Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1, container, true);

                CheckBox checkBox = (CheckBox) findViewById(R.id.parts1Check);
                checkBox.setText("로딩되었습니다.");
            }
        });

    }

}
