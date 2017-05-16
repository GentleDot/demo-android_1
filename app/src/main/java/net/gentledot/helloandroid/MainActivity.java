package net.gentledot.helloandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

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
    public void onButtonInflClicked(View v){
        Intent intent = new Intent(getApplicationContext(), InflationActivity.class);
        startActivity(intent);
    }

    public void onToMenuClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MenuPageActivity.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(), "onActivityResult 메소드 호출됨, 요청코드 : " + requestCode + ", 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();

            if(resultCode == RESULT_OK){
                String name = data.getExtras().getString("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_LONG).show();
            }
        }
    }
}
