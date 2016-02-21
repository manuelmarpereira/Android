package manmarper.com.gps;

import android.app.ProgressDialog;
import android.content.*;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by manel_000 on 19/12/15.
 */
public class MyLocationListener implements LocationListener {

    private final Context context;
    ProgressDialog progressDialog;

    public MyLocationListener(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();  //Save latitude in a double variable
        double longitude = location.getLongitude(); //Save longitude in a double variable

        //Toast to display Coordinates
        Toast.makeText(context, "Latitude = " + latitude + "\nLongitude = " + longitude, Toast.LENGTH_SHORT).show();
        String a = "geo:" + latitude + "," + longitude;

        //Open Maps by parsing coordinates
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(a));
        context.startActivity(i);
        progressDialog.dismiss();

    }


    @Override
    public void onStatusChanged(String s, int i, Bundle b) {
    }

    public void onProviderDisabled(String s) {
        Toast.makeText(context, "GPS is disabled", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String s) {
        Toast.makeText(context, "GPS is enabled", Toast.LENGTH_LONG).show();
    }
}
