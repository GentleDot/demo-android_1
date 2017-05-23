package net.gentledot.helloandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-23.
 */
public class ListFragment extends Fragment {
    String[] values = {"첫 번째 이미지", "두 번째 이미지", "세 번째 이미지"};

    public static interface ImageSelectionCallBack{
        public void onImageSelected(int position);
    }

    public ImageSelectionCallBack callBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.imageList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(callBack != null){
                    callBack.onImageSelected(position);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof ImageSelectionCallBack){
            callBack = (ImageSelectionCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        callBack = null;

        super.onDetach();
    }
}
