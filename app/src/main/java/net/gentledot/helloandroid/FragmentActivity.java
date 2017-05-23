package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import net.gentledot.helloandroid.fragment.MainFragment;
import net.gentledot.helloandroid.fragment.MenuFragment;

public class FragmentActivity extends AppCompatActivity {
    MainFragment fragmentMain;
    MenuFragment fragmentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentMain = new MainFragment();
        fragmentMenu = new MenuFragment();

        Button button = (Button) findViewById(R.id.fragStartBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().add(R.id.fragFrame, fragment1).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, fragmentMain).commit();
            }
        });

        Button button2 = (Button) findViewById(R.id.menuFragStartBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, fragmentMenu).commit();
            }
        });
    }

    public void onFragmentChange(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, fragmentMain).commit();
        } else if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragFrame, fragmentMenu).commit();
        }
    }
}
