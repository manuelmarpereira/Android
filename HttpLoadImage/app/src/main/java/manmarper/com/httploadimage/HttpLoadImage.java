package manmarper.com.httploadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class HttpLoadImage extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    public HttpLoadImage(ImageView imageView) {

        //get ImageView from main activity
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {

        String url = urls[0]; //get url
        Bitmap bitmapImage = null; // bitmap image

        try {
            //decode image to bitmap format
            InputStream in = new java.net.URL(url).openStream();
            bitmapImage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        //return image in bitmap format
        return bitmapImage;
    }

    protected void onPostExecute(Bitmap result) {

        //set image in imageView
        imageView.setImageBitmap(result);
    }

}