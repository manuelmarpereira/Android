package manmarper.com.gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class GPSActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //Initialize buttons
        Button btnLocation = (Button) findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });
    }


    private void getLocation() {

        //verify if gps is enable
        //get location

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            //gps is enable
            //get location

            //create new ProgressDialog
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Obtain your current location, please wait.");
            dialog.show();

            locationListener = new MyLocationListener(this, dialog);

            try {// request location updates using location listener
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { //gps isn't enable
            Toast.makeText(this, "GPS is disabled", Toast.LENGTH_LONG).show(); //show information
        }
    }
}
