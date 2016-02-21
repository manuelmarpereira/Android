package manmarper.com.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioPlayerActivity extends AppCompatActivity {

    boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        //Initialize Buttons
        Button btnPlay = (Button) findViewById(R.id.btn_play);
        Button btnPause = (Button) findViewById(R.id.btn_pause);
        Button btnStop = (Button) findViewById(R.id.btn_stop);

        //Initialize MediaPlayer class instance
        final MediaPlayer mp = MediaPlayer.create(AudioPlayerActivity.this, R.raw.music); //Audio file located in raw directory (music.mp3)

        //Listener on Play Button
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing) {
                    mp.start();
                    playing = true;
                }
            }
        });

        //Listener on Pause Button
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.pause();
                    playing = false;
                }
            }
        });

        //Listener on Stop Button
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.stop();
                    playing = false;
                }
                AudioPlayerActivity.this.finish(); //Exit the Audio Player class
            }
        });


    }


}
