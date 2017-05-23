package net.gentledot.helloandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import net.gentledot.helloandroid.FragmentActivity;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-23.
 */
public class MainFragment extends Fragment {
    FragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (FragmentActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button mainBtn = (Button) rootView.findViewById(R.id.backToFragMenuBtn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange(1);
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }
}
