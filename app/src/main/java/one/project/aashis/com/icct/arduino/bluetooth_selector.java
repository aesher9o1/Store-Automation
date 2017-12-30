package one.project.aashis.com.icct.arduino;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

import javax.crypto.Cipher;

import one.project.aashis.com.icct.API.My_ai;
import one.project.aashis.com.icct.ConnectBT;
import one.project.aashis.com.icct.MainActivity;
import one.project.aashis.com.icct.R;

/**
 * Created by aesher on 11/17/2017.
 */

public class bluetooth_selector extends AppCompatActivity {


    LinearLayout one,two,three;
    ConnectBT connectBT;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.slector);



        one = findViewById(R.id.first);
        two = findViewById(R.id.second);
        three = findViewById(R.id.third);





        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bluetooth_selector.this,bulb_control.class));
            }
        });




        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bluetooth_selector.this,MainActivity.class));
            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bluetooth_selector.this, My_ai.class));

            }
        });
    }
}
