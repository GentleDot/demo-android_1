package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.gentledot.helloandroid.view.CustomDrawablesView;

public class DrawablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);

        CustomDrawablesView view = new CustomDrawablesView(this);
        setContentView(view);
    }
}
