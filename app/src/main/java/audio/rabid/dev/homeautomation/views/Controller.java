package audio.rabid.dev.homeautomation.views;

import android.bluetooth.BluetoothDevice;
import android.widget.CompoundButton;

/**
 * Created by charles on 6/3/15.
 */
public class Controller implements CompoundButton.OnCheckedChangeListener {


    public Controller(BluetoothDevice device){
        //Todo do stuff with it
    }

    public String getName(){
        return "";
    }

    public String getAddress(){
        return "";
    }

    public boolean isOn(){
        return false;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
