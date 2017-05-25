package net.gentledot.helloandroid;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import net.gentledot.helloandroid.fragment.Tab1Fragment;
import net.gentledot.helloandroid.fragment.Tab2Fragment;
import net.gentledot.helloandroid.fragment.Tab3Fragment;

public class TabPage extends AppCompatActivity {
    Tab1Fragment tab1;
    Tab2Fragment tab2;
    Tab3Fragment tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        tab1 = new Tab1Fragment();
        tab2 = new Tab2Fragment();
        tab3 = new Tab3Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, tab1).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("오늘의 뉴스"));
        tabs.addTab(tabs.newTab().setText("기사 저장목록"));
        tabs.addTab(tabs.newTab().setText("기타"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;

                switch (position){
                    case 0:
                        selected = tab1;
                        break;
                    case 1:
                        selected = tab2;
                        break;
                    case 2:
                        selected = tab3;
                        break;
                    default:
                        selected = tab1;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
