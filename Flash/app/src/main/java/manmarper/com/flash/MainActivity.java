package manmarper.com.flash;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera.Parameters;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    boolean isFlashOn = true;
    Camera camera;
    Button btnFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlash = (Button) findViewById(R.id.btn_flash);

        camera = Camera.open(); // open camera

        turnFlashOn(); //turn flash on

        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //at click on button

                if (isFlashOn) {//flash is on

                    //turn flash off
                    turnFlashOff();

                } else {//flash is off

                    //turn flash on
                    turnFlashOn();
                }
            }
        });
    }


    private void turnFlashOn() {

        Parameters parameters = camera.getParameters(); // get parameters
        parameters.setFlashMode(Parameters.FLASH_MODE_TORCH); //set flash mode, in this case torch

        camera.setParameters(parameters); // set new parameters
        camera.startPreview(); // start flash with new parameters

        isFlashOn = true;

        btnFlash.setText("Turn Flash Off"); // change button text
    }

    private void turnFlashOff() {

        Parameters parameters = camera.getParameters(); // get parameters
        parameters.setFlashMode(Parameters.FLASH_MODE_OFF); //set flash mode, in this case torch

        camera.setParameters(parameters); // set new parameters
        camera.startPreview(); // start flash with new parameters

        isFlashOn = false;

        btnFlash.setText("Turn Flash On"); //change button text

    }
}
