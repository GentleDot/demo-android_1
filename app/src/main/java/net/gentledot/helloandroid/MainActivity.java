package net.gentledot.helloandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText toastXPos;
    EditText toastYpos;
    TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastXPos = (EditText) findViewById(R.id.toastXPos);
        toastYpos = (EditText) findViewById(R.id.toastYPos);
        dialogText = (TextView) findViewById(R.id.dialogText);

        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SampleEvent.class);
                startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrientationActivity.class);
                startActivity(intent);
            }
        });

        Button toastBtn = (Button) findViewById(R.id.toastConf);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast toastView = Toast.makeText(getApplicationContext(), "위치가 바뀐 토스트 메시지.", Toast.LENGTH_LONG);

                    int xOffset = Integer.parseInt(toastXPos.getText().toString());
                    int yOffset = Integer.parseInt(toastYpos.getText().toString());

                    toastView.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                    toastView.show();
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });

        Button toastBtn2 = (Button) findViewById(R.id.changeToast);
        toastBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();

                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text = (TextView) layout.findViewById(R.id.toastText);
                text.setText("Hello Android!");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setDuration(Toast.LENGTH_LONG);

                toast.setView(layout);
                toast.show();
            }
        });

        Button snackBtn = (Button) findViewById(R.id.snackMaker);
        snackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show();
            }
        });

        Button dialogBtn = (Button) findViewById(R.id.dialogButton);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });

        Button progressBtn = (Button) findViewById(R.id.progressBtn);
        progressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProgressActivity.class);
                startActivity(i);
            }
        });

        Button simpleAniBtn = (Button) findViewById(R.id.simpleAniBtn);
        simpleAniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SimpleAnimation.class);
                startActivity(i);
            }
        });
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "대화상자의 응답으로 예 를 눌렀습니다.";
                dialogText.setText(message);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "대화상자의 응답으로 아니오 를 눌렀습니다.";
                dialogText.setText(message);
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼을 눌렀습니다.";
                dialogText.setText(message);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
