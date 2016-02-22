package manmarper.com.httploadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText txtURl;
    ImageView imgLoaded;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtURl = (EditText) findViewById(R.id.txt_url);
        imgLoaded = (ImageView) findViewById(R.id.img_loaded);

        btnLoad = (Button) findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // at click on button
                //make new request to load an image using AsyncTask
                new HttpLoadImage(imgLoaded).execute(txtURl.getText().toString());
            }
        });
    }
}
