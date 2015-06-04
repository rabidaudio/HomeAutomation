package audio.rabid.dev.homeautomation.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import audio.rabid.dev.homeautomation.EasyArrayAdapter;
import audio.rabid.dev.homeautomation.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by charles on 6/3/15.
 *
 * Draws a list of {@link Controller}s.
 */
public class BluetoothControlArrayAdapter extends EasyArrayAdapter<Controller, BluetoothControlArrayAdapter.ViewHolder> {

    public BluetoothControlArrayAdapter(Context context, @Nullable ArrayList<Controller> controllers){
        super(context, R.layout.bluetooth_controller, controllers);
    }

    @Override
    protected void onDrawView(Controller c, ViewHolder h) {
        h.deviceName.setText(c.getName());
        h.macAddress.setText(c.getAddress());
        h.switchCtrl.setChecked(c.isOn());
        h.switchCtrl.setOnCheckedChangeListener(c);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new ViewHolder(v);
    }

    protected class ViewHolder {
        @InjectView(R.id.deviceName) TextView deviceName;
        @InjectView(R.id.macAddress) TextView macAddress;
        @InjectView(R.id.switchCtrl) Switch switchCtrl;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
