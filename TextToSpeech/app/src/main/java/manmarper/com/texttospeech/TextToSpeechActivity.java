package manmarper.com.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        final EditText txtInput = (EditText) findViewById(R.id.txt_input);

        Button btnSpeak = (Button) findViewById(R.id.btn_speak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                convertTTS(txtInput.getText().toString());

            }
        });

        //init text to voice conversion
        textToSpeech = new TextToSpeech(this, this);
    }


    private void convertTTS(String text) {
        //reproduce audio after text conversion

        if (text == null || "".equals(text)) {
            text = "No text was written";
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        //callback method used after init TTS motor

        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(TextToSpeechActivity.this, "Language not supported", Toast.LENGTH_SHORT).show();
            } else {
                convertTTS("Hello, write some text and press the button");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //free resources used by the TTS Motor
        textToSpeech.shutdown();
    }
}
