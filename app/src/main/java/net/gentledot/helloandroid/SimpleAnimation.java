package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class SimpleAnimation extends AppCompatActivity {
    TextView textView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_animation);

        textView = (TextView) findViewById(R.id.aniText1);
       animation = AnimationUtils.loadAnimation(this, R.anim.flow);

       Button button = (Button) findViewById(R.id.aniButton);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               textView.startAnimation(animation);
           }
       });
    }
}
