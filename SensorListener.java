package salma.mah.se.sensorslist;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class SensorListener extends AppCompatActivity implements SensorEventListener {

    private Sensor sensor;
    private SensorManager mSensorManager;
    private View view;
    private Activity activity;
    private TextView tvValue0;
    private TextView tvValue1;
    private TextView tvValue2;
    private TextView tvValue3;
    private TextView tvValue4;
    private TextView tvTimeStamp;
    private TextView tvAccuracy;
    private boolean isRunning = false;
    private boolean noValues = false;
    SensorUnit sUnit;
    String[] units;
    String text;

    private ArrayList<SensorEvent> sensorEvents;

    public SensorListener(Activity activity, View view, Sensor sensor){

        this.sensor = sensor;
        this.view = view;
        this.activity = activity;

        sUnit = new SensorUnit(this.sensor);
        units = sUnit.getUnit();

        initTextView();

        //If the class SensorUnit dont have the units for the sensors values
        //no values will be shown for the sensor
        if(units[0].equals("MissingSensor")) {

            noValues = true;
            text = "No values for this sensor";
            setText(0, text);
        }

        else {

            mSensorManager = (SensorManager) activity.getSystemService(activity.SENSOR_SERVICE);
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

            isRunning = true;

            // Show Alert 
            Toast.makeText(activity, "Started reading from " + sensor.getName() , Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        String[] str;

        for (int i = 0; i < units.length; i++) {

            //Splits the string
            str = units[i].split(",");

            //Checks if the unit show be displayed after or before the sensor value
            if (str[0].equals(" ")) {
                text = (event.values[i] + str[1]);
            } else {
                text = (str[0] + event.values[i]);
            }

            //Set the text to the right textview
            setText(i, text);

            //The time from the sensor reading
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
            String timeStamp = formatter.format(new Date(event.timestamp / 1000000));

            //Displays the time stamp and accuracy
            tvTimeStamp.setText("TimStamp: " + timeStamp);
            tvAccuracy.setText("Accuracy: " + event.accuracy);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void setText(int tvValue, String text){

        switch(tvValue){

            case 0:
                tvValue0.setText(text);
                break;

            case 1:
                tvValue1.setText(text);
                break;

            case 2:
                tvValue2.setText(text);
                break;

            case 3:
                tvValue3.setText(text);
                break;

            case 4:
                tvValue4.setText(text);
                break;
        }
    }

    /**
     * Resume the sensor reading
     */
    public void resume(){

        if(isRunning == false && noValues == false) {
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            isRunning = true;

            Toast.makeText(activity, "Started reading from " + sensor.getName() , Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Pause the sensor reading
     */
    public void pause(){

        if(noValues == false && isRunning == true) {
            mSensorManager.unregisterListener(this);
            isRunning = false;

            Toast.makeText(activity, "Stopped reading from " + sensor.getName() , Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Destroy the sensor reading
     */
    public void destroy(){

        if(noValues == false) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
            sensor = null;
        }
    }

    /**
     * Creates the textviews
     */
    public void initTextView(){

        tvValue0 = (TextView) view.findViewById(R.id.tvValue0);
        tvValue1 = (TextView) view.findViewById(R.id.tvValue1);
        tvValue2 = (TextView) view.findViewById(R.id.tvValue2);
        tvValue3 = (TextView) view.findViewById(R.id.tvValue3);
        tvValue4 = (TextView) view.findViewById(R.id.tvValue4);
        tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
        tvAccuracy = (TextView) view.findViewById(R.id.tvAccuracy);
    }
}
