package net.gentledot.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ParcelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);

        Button parcelBack = (Button) findViewById(R.id.parcel_back);
        parcelBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            ArrayList<String> names = (ArrayList<String>)intent.getSerializableExtra("names");
            if(names != null){
                Toast.makeText(getApplicationContext(), "전달받은 이름 리스트 갯수 : " + names.size(), Toast.LENGTH_LONG).show();
            }

            SimpleData data = intent.getParcelableExtra("data");
            if(data != null){
                Toast.makeText(getApplicationContext(), "전달받은 SimpleData : " + data.getMessage() ,Toast.LENGTH_LONG).show();
            }
        }
    }
}
