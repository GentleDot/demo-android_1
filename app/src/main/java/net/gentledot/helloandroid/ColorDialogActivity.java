package net.gentledot.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import net.gentledot.helloandroid.gallery.CoverFlow;
import net.gentledot.helloandroid.listeners.OnColorSelectedListener;
import net.gentledot.helloandroid.view.ColorItemView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorDialogActivity extends AppCompatActivity {
    private static final String TAG = "ColorDialogActivity";

    GridView gridView;
    ColorAdapter colorAdapter;

    public static OnColorSelectedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_dialog);

       gridView = (GridView) findViewById(R.id.colorGrid);

       colorAdapter = new ColorAdapter();
       colorAdapter.addItem(0xff000000);
       colorAdapter.addItem(0xffff0000);
       colorAdapter.addItem(0xffffff00);
       colorAdapter.addItem(0xff00ffff);
       colorAdapter.addItem(0xff0000ff);

       gridView.setAdapter(colorAdapter);

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               int selectedColor = (Integer) colorAdapter.getItem(position);

               Intent intent = new Intent(getApplicationContext(), PaintBoardActivity.class);
               intent.putExtra("color", selectedColor);
               setResult(Activity.RESULT_OK, intent);
               finish();
           }
       });

    }

    class ColorAdapter extends BaseAdapter{
        ArrayList<Integer> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ColorItemView view = null;
            if(convertView == null){
                view = new ColorItemView(getApplicationContext());
            } else{
                view = (ColorItemView) convertView;
            }

            int color = (Integer) items.get(position);
//            Log.d(TAG, "settedColor = " + color);
            view.setColor(color);

            return view;
        }

        public void addItem(int color){
            items.add(color);
        }
    }
}
