package audio.rabid.dev.homeautomation.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import audio.rabid.dev.homeautomation.EasyArrayAdapter;
import audio.rabid.dev.homeautomation.R;

/**
 * Created by charles on 6/3/15.
 *
 * Draws a list of {@link Controller}s.
 */
public class BluetoothControlArrayAdapter extends EasyArrayAdapter<Controller> {

    private static final int LAYOUT_ID = R.layout.bluetooth_controller; //the layout to use as the base

    Context parent;
    ArrayList<Controller> controllers;

    public BluetoothControlArrayAdapter(Context context, @Nullable ArrayList<Controller> controllers){
        super(context, LAYOUT_ID, controllers, new AdapterDrawCallback<Controller>() {
            @Override
            public void onDrawView(Controller object, HashMap<String, View> viewSet) {
                ((TextView) viewSet.get("deviceName")).setText(object.getName());
                ((TextView) viewSet.get("macAddress")).setText(object.getAddress());
                Switch switchCtrl = (Switch) viewSet.get("switchCtrl");
                switchCtrl.setChecked(object.isOn());
                switchCtrl.setOnCheckedChangeListener(object);
            }
        });
        this.parent = context;
        this.controllers = (controllers==null ? new ArrayList<Controller>() : controllers);
    }

    @Override
    protected HashMap<String, Integer> getViewIds(){
        HashMap<String, Integer> h = new HashMap<>();
        h.put("deviceName", R.id.deviceName);
        h.put("macAddress", R.id.macAddress);
        h.put("switchCtrl", R.id.switchCtrl);
        return h;
    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup viewGroup) {
//        LayoutInflater inflater = LayoutInflater.from(parent);
//
//        View rowView;
//        ViewHolder h;
//        if (view == null) {
//            rowView = inflater.inflate(LAYOUT_ID, viewGroup, false);
//            h = new ViewHolder(rowView);
//            rowView.setTag(h);
//        } else {
//            rowView = view;
//            h = (ViewHolder) rowView.getTag();
//            if (h == null) {
//                //for some reason, no holder attached
//                h = new ViewHolder(rowView);
//                rowView.setTag(h);
//            }
//        }
//        Controller c = controllers.get(position);
//        //draw the result
//        h.deviceName.setText(c.getName());
//        h.macAddress.setText(c.getAddress());
//        h.switchCtrl.setChecked(c.isOn());
//        h.switchCtrl.setOnCheckedChangeListener(c);
//        return rowView;
//    }

//    private class ViewHolder {
//        TextView deviceName;
//        TextView macAddress;
//        Switch switchCtrl;
//        public ViewHolder(View v) {
//            deviceName = (TextView) v.findViewById(R.id.deviceName);
//            macAddress = (TextView) v.findViewById(R.id.macAddress);
//            switchCtrl = (Switch) v.findViewById(R.id.switchCtrl);
//        }
//    }
}
