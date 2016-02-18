package manmarper.com.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btnShow = (Button) findViewById(R.id.btn_show);
        final EditText txtName= (EditText) findViewById(R.id.txt_name);
        final EditText txtLocation= (EditText) findViewById(R.id.txt_location);

        //Set listener on notification button
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create new notification
                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.location_ic)
                                .setContentTitle("Hello "+txtName.getText())//title
                                .setContentText("You are in "+txtLocation.getText()+ " now!"); //content text

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build()); //notify
            }

        });

    }
}
