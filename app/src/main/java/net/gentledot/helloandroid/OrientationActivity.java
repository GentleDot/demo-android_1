package net.gentledot.helloandroid;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OrientationActivity extends AppCompatActivity {
    EditText editText01;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        showToast("onCreate 호출됨.");

        editText01 = (EditText) findViewById(R.id.editText01);

        Button btn = (Button) findViewById(R.id.button01);
        if (btn != null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = editText01.getText().toString();
                    Toast.makeText(getApplicationContext(), "입력된 값을 변수에 저장했습니다. : " + name, Toast.LENGTH_LONG).show();
                }
            });
        }


        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            if(editText01 != null){
                editText01.setText(name);
                Toast.makeText(getApplicationContext(), "값을 복원했습니다. : " + name, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart 호출됨.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop 호출됨.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy 호출됨.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause 호출됨.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume 호출됨.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", name);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("방향 : ORIENTATION_LANDSCAPE (가로 방향)");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("방향 : ORIENTATION_PORTRAIT (세로 방향)");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
