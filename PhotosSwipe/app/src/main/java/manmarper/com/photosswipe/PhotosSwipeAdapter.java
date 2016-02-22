package manmarper.com.photosswipe;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by manel_000 on 21/12/15.
 */
public class PhotosSwipeAdapter extends PagerAdapter {


    private int[] image_resources = {R.drawable.image1, R.drawable.image2, R.drawable.image3};//image list for add to the photos swipe
    private Context context;

    public PhotosSwipeAdapter(Context context) {

        this.context = context;//get the App context
    }

    @Override
    public int getCount() {

        //get the number of images
        return image_resources.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageViewSwipe);
        imageView.setImageResource(image_resources[position]); //set image from list
        container.addView(imageView); //add image to the new page
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }


}
