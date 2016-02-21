package manmarper.com.network;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo;
    Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = (TextView) findViewById(R.id.txt_info);

        btnVerify = (Button) findViewById(R.id.btn_verify);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //click on button
                //verify connection
                networkVerification();
            }
        });

        networkVerification(); // init connection verification
    }


    private void networkVerification() {

        // verify network connection

        if (isNetworkAvailable()) {
            //connected
            setNetworkAvailable();

        } else {
            //not connected
            setNetworkUnavailable();
        }

    }


    private boolean isNetworkAvailable() {

        //verify wifi and data connection

        boolean connected = false;

        ConnectivityManager cm;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        cm.getActiveNetworkInfo();

        connected = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() || cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();

        //return true  - WIFI Connection = true or mobile connection = true
        //return false - Wifi connection = false and mobile connection = false
        return connected;
    }


    private void setNetworkAvailable() {

        txtInfo.setText("Connected"); // set info connected
        txtInfo.setTextColor(Color.GREEN); // change text color to green

    }


    private void setNetworkUnavailable() {
        txtInfo.setText("No Network Connection"); // set info no connection
        txtInfo.setTextColor(Color.RED); //change text color to Red

    }
}
