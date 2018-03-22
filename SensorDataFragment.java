package salma.mah.se.sensorslist;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorDataFragment extends Fragment {

    private View myView;
    private TextView tvSensorName;
    private TextView tvSensorVendor;
    private TextView tvSensorVersion;
    private TextView tvSensorMinDelay;
    private TextView tvSensorMaxDelay;
    private TextView tvSensorRange;
    private TextView tvSensorPower;
    private TextView tvSensorWakeUp;
    private TextView tvSensorResolution;
    private Button btnStart;
    private Button btnStop;
    private SensorManager sensorM;
    private Sensor sensor;
    private SensorListener sensorListener;


    public SensorDataFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_sensor_data, container, false);

        tvSensorName = (TextView) myView.findViewById(R.id.tvSensorName);
        tvSensorVendor = (TextView) myView.findViewById(R.id.tvSensorVendor);
        tvSensorVersion = (TextView) myView.findViewById(R.id.tvSensorVersion);
        tvSensorMinDelay = (TextView) myView.findViewById(R.id.tvSensorMinDelay);
        tvSensorMaxDelay = (TextView) myView.findViewById(R.id.tvSensorMaxDelay);
        tvSensorRange = (TextView) myView.findViewById(R.id.tvSensorRange);
        tvSensorPower = (TextView) myView.findViewById(R.id.tvSensorPower);
        tvSensorWakeUp = (TextView) myView.findViewById(R.id.tvSensorWakeUp);
        tvSensorResolution = (TextView) myView.findViewById(R.id.tvSensorResolution);

        btnStart = (Button) myView.findViewById(R.id.btnStart);
        btnStop = (Button) myView.findViewById(R.id.btnStop);

        init();
        registerListeners();

        return myView;
    }


    public void init(){

        //gets the sensor value from the clicked sensor in the list
        int myValue = this.getArguments().getInt("sensor");

        //Creates a sensor from the sensorID
        sensorM = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorM.getDefaultSensor(myValue);

        //Creates new sensor listener
        sensorListener = new SensorListener(getActivity(), myView, sensor);

        //Stores all sensor information in variables
        String name = sensor.getName();
        String vendor = sensor.getVendor();
        int version = sensor.getVersion();
        int minDelay = sensor.getMinDelay();
        int maxDelay = sensor.getMaxDelay();
        float range = sensor.getMaximumRange();
        float power = sensor.getPower();
        boolean isWakeUpSensor = sensor.isWakeUpSensor();
        float resolution = sensor.getResolution();

        //set text in all text views
        tvSensorName.setText(name);
        tvSensorVendor.setText("Vendor: " + vendor);
        tvSensorVersion.setText("Version: " + version);
        tvSensorMinDelay.setText("Minimum delay: " + minDelay);
        tvSensorMaxDelay.setText("Maximum delay: " + maxDelay);
        tvSensorRange.setText("Maximum range: " + range);
        tvSensorPower.setText("Power consumption: " + power);
        tvSensorWakeUp.setText("Is wake up sensor: " + isWakeUpSensor);
        tvSensorResolution.setText("Resolution: " + resolution);
    }

    /**
     * Register listeners with start and stop button
     */
    private void registerListeners() {
        View.OnClickListener clickListener = new ButtonListener();
        btnStart.setOnClickListener(clickListener);
        btnStop.setOnClickListener(clickListener);
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStart:
                    sensorListener.resume();
                    break;

                case R.id.btnStop:
                    sensorListener.pause();
                    break;
            }
        }
    }

    public void onResume(){
        super.onResume();
    }

    public void onPause(){
        super.onPause();
        sensorListener.pause();
    }

    public void onDestroy(){
        super.onDestroy();
        sensorListener.destroy();
    }
}

