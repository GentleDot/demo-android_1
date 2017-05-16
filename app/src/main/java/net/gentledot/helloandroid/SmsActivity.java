package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class SmsActivity extends AppCompatActivity {
    EditText message;
    TextView countByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        message = (EditText) findViewById(R.id.inputTxt);
        countByte = (TextView) findViewById(R.id.chkByte);

        Button sendButton = (Button) findViewById(R.id.sendMsg);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = message.getText().toString();
                Toast.makeText(getApplicationContext(), "다음의 메시지가 전송되었습니다. \n\n" + text, Toast.LENGTH_LONG).show();
            }
        });

        Button closeButton = (Button) findViewById(R.id.cancel);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    byte[] bytes = null;
                    try {
                        bytes = s.toString().getBytes("UTF-8");
                        int strCount = bytes.length;
                        countByte.setText(strCount + " / 80 바이트");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String str = s.toString();
                    try {
                        byte[] strBytes = str.getBytes("UTF-8");
                        if(strBytes.length > 80){
                            s.delete(s.length() - 2, s.length() - 1);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
            }
        };

        message.addTextChangedListener(watcher);

    }
}
