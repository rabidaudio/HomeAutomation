package audio.rabid.dev.homeautomation.views;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.CompoundButton;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

/**
 * Created by charles on 6/3/15.
 */
public class Controller implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = Controller.class.getSimpleName();

    private static final UUID SERIAL_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private boolean checked = false;
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private BufferedReader input;
    private BufferedWriter output;

    public Controller(BluetoothDevice device){
        this.device = device;
    }

    public String getName(){
        return device.getName();
    }

    public String getAddress(){
        return device.getAddress();
    }

    public boolean isPaired(){
        return device.getBondState() == BluetoothDevice.BOND_BONDED;
    }

    public void connect() throws IOException {
        socket = device.createRfcommSocketToServiceRecord(SERIAL_UUID);
        socket.connect();
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        output.write("Hello!");
        output.newLine();
        output.flush();
        Log.d(TAG, input.readLine());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checked = !checked;
    }
}
