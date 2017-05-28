package net.gentledot.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import net.gentledot.helloandroid.item.InfoItem;
import net.gentledot.helloandroid.item.InfoItemView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    InfoAdapter adapter;
    EditText nameText;
    EditText telText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView) findViewById(R.id.listView1);
        nameText = (EditText) findViewById(R.id.inputNameInfo);
        telText = (EditText) findViewById(R.id.inputTelInfo);

        Button button = (Button) findViewById(R.id.listShowBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String mobile = telText.getText().toString();

                adapter.addItem(new InfoItem(name, mobile));
                adapter.notifyDataSetChanged();
            }
        });


        adapter = new InfoAdapter();
        for (int i = 1; i <= 10; i++){
            adapter.addItem(new InfoItem("Name_" + i, "010-" + i + "000-" + "100" + i ));
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoItem item = (InfoItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
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
