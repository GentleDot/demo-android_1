package net.gentledot.helloandroid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class ProgressActivity extends AppCompatActivity {
    EditText setProgress;
    ProgressBar progressInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        setProgress = (EditText) findViewById(R.id.progressText);
        progressInfo = (ProgressBar) findViewById(R.id.progressBar);

        Button btn = (Button) findViewById(R.id.progressStartBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = setProgress.getText().toString();
                int input = Integer.parseInt(inputStr);

                progressInfo.setProgress(input);
            }
        });

        Button btn2 = (Button) findViewById(R.id.loadingProgressBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setProgress.setText(String.valueOf(progress));
                setBrightness(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setBrightness(int progress) {
        if (progress < 10){
            progress = 10;
        } else if (progress > 100){
            progress = 100;
        }

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) progress / 100;
        getWindow().setAttributes(params);
    }

    private void showProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("데이터 확인중.");
        dialog.show();
        /*dialog.dismiss(); // 프로그레스 없애기*/
    }
}
