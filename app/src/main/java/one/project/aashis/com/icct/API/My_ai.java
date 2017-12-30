package one.project.aashis.com.icct.API;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Status;
import ai.api.ui.AIButton;
import one.project.aashis.com.icct.ConnectBT;
import one.project.aashis.com.icct.R;

/**
 * Created by aesher on 11/18/2017.
 */

public class My_ai extends AppCompatActivity implements AIButton.AIButtonListener {

    private AIButton aiButton;
    private EditText textView;
    ConnectBT connectBT;
    TextView MANIPAL,EAI;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assistant);
        connectBT = new ConnectBT(this);
        connectBT.execute();

        aiButton = findViewById(R.id.micButton);
        textView = findViewById(R.id.text);
        final ai.api.android.AIConfiguration config = new ai.api.android.AIConfiguration("0117162701c041c784a85e1dacdf982c",
                ai.api.android.AIConfiguration.SupportedLanguages.Spanish,
                ai.api.android.AIConfiguration.RecognitionEngine.System);

        config.setRecognizerStartSound(getResources().openRawResourceFd(R.raw.test_start));
        config.setRecognizerStopSound(getResources().openRawResourceFd(R.raw.test_stop));
        config.setRecognizerCancelSound(getResources().openRawResourceFd(R.raw.test_cancel));

        aiButton.initialize(config);
        aiButton.setResultsListener(this);


        MANIPAL = findViewById(R.id.manipal);
        EAI = findViewById(R.id.ICCT);

        MANIPAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBT.turnOnLed("Manipal");
            }
        });


        EAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectBT.turnOnLed("EAI");
            }
        });

        isStoragePermissionGranted();


    }

    @Override
    protected void onPause() {
        super.onPause();
        aiButton.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aiButton.resume();
    }


    public void isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
        }
    }

    @Override
    public void onResult(final AIResponse result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // textView.setText(gson.toJson(result));
                final Status status = result.getStatus();

                ai.api.model.Result RESULT = result.getResult();
                String textS= RESULT.getFulfillment().getSpeech();

                connectBT.turnOnLed("!"+textS);

            }
        });
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onCancelled() {

    }
}
