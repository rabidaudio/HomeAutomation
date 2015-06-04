package audio.rabid.dev.homeautomation.views;

import android.bluetooth.BluetoothDevice;
import android.widget.CompoundButton;

/**
 * Created by charles on 6/3/15.
 */
public class Controller implements CompoundButton.OnCheckedChangeListener {

    private boolean checked = false;
    private BluetoothDevice device;

    public Controller(BluetoothDevice device){
        this.device = device;
    }

    public String getName(){
        return "name";
    }

    public String getAddress(){
        return "mac";
    }

    public boolean isOn(){
        return checked;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checked = !checked;
    }
}
