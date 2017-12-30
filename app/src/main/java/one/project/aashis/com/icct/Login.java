package one.project.aashis.com.icct;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import one.project.aashis.com.icct.arduino.bluetooth_selector;

/**
 * Created by aesher on 11/21/2017.
 */

public class Login extends AppCompatActivity {


    EditText editText;
    Button button;
    String send;

    ConnectBT connectBT;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editText = findViewById(R.id.user_name);
        button = findViewById(R.id.login);
        connectBT = new ConnectBT(this);

        connectBT.execute();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = editText.getText().toString();

                switch (send) {
                    case "Tushar":
                        connectBT.turnOnLed("tushar");
                        startActivity(new Intent(Login.this, bluetooth_selector.class));
                        connectBT.SMILE();
                        Log.w("name","tushar");
                        break;
                    case "Aashis":
                        connectBT.turnOnLed("Aashis");
                        startActivity(new Intent(Login.this, bluetooth_selector.class));
                        break;
                    case "Vidu":
                        connectBT.turnOnLed("Vidu");
                        startActivity(new Intent(Login.this, bluetooth_selector.class));
                        break;
                    case "Sid":
                        connectBT.turnOnLed("Sid");
                        startActivity(new Intent(Login.this, bluetooth_selector.class));
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"No user Exists",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
