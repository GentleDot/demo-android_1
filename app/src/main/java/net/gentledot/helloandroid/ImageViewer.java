package net.gentledot.helloandroid;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.gentledot.helloandroid.fragment.ImageViewerFragment;
import net.gentledot.helloandroid.fragment.ListFragment;

public class ImageViewer extends AppCompatActivity implements ListFragment.ImageSelectionCallBack {
    ListFragment listFragment;
    ImageViewerFragment imageViewerFragment;
    android.support.v4.app.FragmentManager manager;

    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        manager = getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        imageViewerFragment = (ImageViewerFragment) manager.findFragmentById(R.id.imageViewerFragment);

    }

    @Override
    public void onImageSelected(int position) {
        imageViewerFragment.setImage(images[position]);
    }
}
