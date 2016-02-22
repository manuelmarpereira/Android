package manmarper.com.photosswipe;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PhotosSwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_swipe);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PhotosSwipeAdapter adapter = new PhotosSwipeAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

    }
}
