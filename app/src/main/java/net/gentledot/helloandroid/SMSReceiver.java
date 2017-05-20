package net.gentledot.helloandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReceiver";

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 메소드 호출됨." );

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMssage(bundle);

        if(messages != null && messages.length > 0){
            // SMS 발신 번호 확인
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "SMS Sender : " + sender);

            //SMS 메시지 확인
            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "SMS received Date : " + receivedDate);

            sendToActivity(context, sender, contents, format.format(receivedDate));
        }

    }

    private void sendToActivity(Context context, String sender, String contents, String receivedDate) {
        Intent intent = new Intent(context, SMSReceiveActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receivedDate", receivedDate);

        context.startActivity(intent);
    }


    private SmsMessage[] parseSmsMssage(Bundle bundle) {
        // PDU 포맷으로 된 메시지
        Object[] objs = (Object[]) bundle.get("pdus");
        int smsCount = objs.length;
        SmsMessage[] messages = new SmsMessage[smsCount];

        for(int i = 0; i < smsCount; i++){
            // PDU 포맷의 메시지를 복원
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
