package net.gentledot.helloandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View view){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }
    public void onButton2Clicked(View view){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
        startActivity(myIntent);
    }
    public void onButton3Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
    public void onButtonScrClicked(View v){
        Intent intent = new Intent(getApplicationContext(), Scroll.class);
        startActivity(intent);
    }
    public void onButtonFraClicked(View v){
        Intent intent = new Intent(getApplicationContext(), FrameActivity.class);
        startActivity(intent);
    }
    public void onButtonSmsClicked(View v){
        Intent intent = new Intent(getApplicationContext(), SmsActivity.class);
        startActivity(intent);
    }


}
