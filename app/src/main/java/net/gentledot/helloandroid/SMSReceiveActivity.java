package net.gentledot.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMSReceiveActivity extends AppCompatActivity {
    EditText senderBox;
    EditText contentBox;
    EditText receivedDateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        senderBox = (EditText) findViewById(R.id.smsSender);
        contentBox = (EditText) findViewById(R.id.smsContent);
        receivedDateBox = (EditText) findViewById(R.id.smsReceivedDate);

        Button button = (Button) findViewById(R.id.smsConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            senderBox.setText(sender);
            contentBox.setText(contents);
            receivedDateBox.setText(receivedDate);
        }
    }

}
