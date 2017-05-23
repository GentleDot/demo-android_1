package net.gentledot.helloandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import net.gentledot.helloandroid.R;

/**
 * Created by Sang on 2017-05-24.
 */
public class ImageViewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image_viewer, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageViewArea);

        return rootView;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
