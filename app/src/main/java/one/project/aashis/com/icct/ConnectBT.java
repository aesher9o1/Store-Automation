package one.project.aashis.com.icct;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created by aesher on 11/17/2017.
 */

public class ConnectBT extends AsyncTask<Void, Void, Void> {

    private String address = "98:D3:32:30:A8:A5";
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Context mContext;


    public ConnectBT(Context mContext) {
        this.mContext = mContext;
    }

    private boolean ConnectSuccess = true;
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... devices)
    {
        try {
            if (btSocket == null || !isBtConnected) {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();
                BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                btSocket.connect();
            }
        } catch (IOException e) {
            ConnectSuccess = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);

        if (!ConnectSuccess) {
           Toast.makeText(mContext,"Please Restart",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Connected", Toast.LENGTH_SHORT).show();
            isBtConnected = true;
        }

    }

    public boolean isBtConnected() {
        return isBtConnected;
    }


    //Communication to arduino
    public void turnOnLed( String send)
    {

        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write(send.getBytes());
            }
            catch (IOException e) {}
        }
     else {
            Toast.makeText(mContext,"NULL",Toast.LENGTH_SHORT).show();
        }
    }

     public void SMILE() {


         try {
             btSocket.close();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }


}
