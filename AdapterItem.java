package salma.mah.se.sensorslist;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Salma on 2017-01-27.
 */

public class AdapterItem extends ArrayAdapter<Sensor> {

    private Context mContext;
    private int layoutResId;
    public String category;
    private ArrayList<Sensor> sensorList;


    public AdapterItem(Context mContext, int layoutResourceId, ArrayList<Sensor> sensorList) {

        super(mContext, layoutResourceId, sensorList);

        this.layoutResId = layoutResourceId;
        this.mContext = mContext;
        this.sensorList = sensorList;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Sensor temp = sensorList.get(position);
        String sensorName = temp.getName();
        String vendorName = temp.getVendor();

        System.out.println(temp.getName() + ": " + temp.getType());

        if(convertView==null){

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResId, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvSensor);
        TextView tvVendor = (TextView) convertView.findViewById(R.id.tvVendor);

        tvTitle.setText(sensorName);
        tvVendor.setText(vendorName);

        return convertView;
    }
}
