package audio.rabid.dev.homeautomation;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 99; //fuckin magic numbers

    private BluetoothAdapter adapter;

    private ArrayList<BluetoothDevice> connectedAutomationDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            // Device does not support Bluetooth
            unsupported();
            return;
        }

        if (!adapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else{
            //open connection
        }
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
                    //bluetooth is now enabled
                }else{
                    unsupported();
                }
        }
    }


    private void unsupported(){
        Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_LONG).show();
        finish();
    }

    private void findDevice(){
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            if(isAutomationDevice(device)){
                connectedAutomationDevices.add(device);
            }
        }


    }


    private static boolean isAutomationDevice(BluetoothDevice device){
        return device.getName().startsWith("HC"); //TODO
    }

}
