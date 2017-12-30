package one.project.aashis.com.icct.arduino;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;


import one.project.aashis.com.icct.ConnectBT;
import one.project.aashis.com.icct.R;

/**
 * Created by aesher on 11/17/2017.
 */

public class bulb_control extends AppCompatActivity {

    LinearLayout one,two,three;

    boolean isONE= false;
    boolean isTWO = false;
    boolean isTHREE = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.bulb);
        one = findViewById(R.id.first);
        two = findViewById(R.id.second);
        three = findViewById(R.id.third);


        final ConnectBT bluetooth = new ConnectBT(this);

        if(!bluetooth.isBtConnected()){
            bluetooth.execute();
        }




        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isONE){
                    isONE= true;
                    bluetooth.turnOnLed("Oon");

                }
                else{
                    isONE = false;
                    bluetooth.turnOnLed("Ooff");
                }
            }
        });



        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTWO){
                    isTWO = true;
                    bluetooth.turnOnLed("Ton");
                }
                else{
                    isTWO = false;
                    bluetooth.turnOnLed("Toff");
                }
            }
        });



        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isTHREE){
                    bluetooth.turnOnLed("THon");
                    isTHREE = true;
                }
                else{
                    bluetooth.turnOnLed("THoff");
                    isTHREE= false;
                }
            }
        });
    }
}
