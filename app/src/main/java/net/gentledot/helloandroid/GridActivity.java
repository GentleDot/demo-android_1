package net.gentledot.helloandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;
import net.gentledot.helloandroid.item.InfoItem;
import net.gentledot.helloandroid.item.InfoItemView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {
    InfoAdapter adapter = new InfoAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        final GridView gridView = (GridView) findViewById(R.id.gridArea);

        adapter.addItem(new InfoItem("가나다", "010-0001-0002"));
        adapter.addItem(new InfoItem("라마바", "010-1112-2224"));
        adapter.addItem(new InfoItem("사아자", "010-2213-5213"));
        adapter.addItem(new InfoItem("차카타", "010-2322-0042"));
        adapter.addItem(new InfoItem("파하", "010-6621-5121"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoItem item = (InfoItem) adapter.getItem(position);
                String name = item.getName();

                Toast.makeText(GridActivity.this, "클릭한 뷰 내용 : " + name, Toast.LENGTH_LONG).show();
            }
        });
    }

    class InfoAdapter extends BaseAdapter{
        ArrayList<InfoItem> items = new ArrayList<>();

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
            InfoItemView view = null;
            if(convertView == null){
                view = new InfoItemView(getApplicationContext());
            } else {
                view = (InfoItemView) convertView;
            }

            InfoItem item = items.get(position);
            view.setName(item.getName());
            view.setTelNumber(item.getMobile());

            return view;
        }

        public void addItem(InfoItem item){
            items.add(item);
        }
    }

}
