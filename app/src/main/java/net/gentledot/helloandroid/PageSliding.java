package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class PageSliding extends AppCompatActivity {
    LinearLayout page;
    Animation transLeft;
    Animation transRight;
    Button button;

    boolean isPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sliding);

        page = (LinearLayout) findViewById(R.id.page);
        transLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        transRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        transLeft.setAnimationListener(listener);
        transRight.setAnimationListener(listener);

        button = (Button) findViewById(R.id.slideLayoutBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    page.startAnimation(transRight);
                } else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(transLeft);
                }
            }
        });
    }

    class SlidingAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);

                button.setText("열기");
                isPageOpen = false;
            } else {
                button.setText("닫기");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}


