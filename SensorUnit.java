package salma.mah.se.sensorslist;

import android.hardware.Sensor;

/**
 * Created by Salma on 6/6/2017.
 */

public class SensorUnit {
    Sensor sensor;

    // The "," tells on which side of the value the unit should be

    //Accelerometer, Magnetic Field
    String[] xyz = {"X: , ","Y: , ","Z: , "};
    String[] light = {" , LUX"};
    String[] pressure = {" , hPa"};
    String[] proximity = {" , Centimeters"};
    String[] rotationVector = {"X: , ", "Y: , ", "Z: , " , "Accuracy: , "};
    String[] oriantation = {" , ", " , ", " , "};
    String[] humidity = {" , %"};
    String[] temperature = {" ,°C"};
    //The returned string when the sensor value cant be found
    String[] missingSensor = {"MissingSensor"};

    public SensorUnit(Sensor sensor){

        this.sensor = sensor;

    }

    /**
     * Returns the right units for a sensor based on the sensor type
     * @return
     */
    public String[] getUnit(){

        switch(sensor.getType()){

            //Accelerometer
            case 1:
                return xyz;

            //Magnetic field sensor
            case 2:
                return xyz;

            //Orientation sensor
            case 3:
                return oriantation;

            //Light sensor
            case 5:
                return light;

            //Pressure sensor
            case 6:
                return pressure;

            //Temperature sensor
            case 7:
                return temperature;

            //Proximity sensor
            case 8:
                return proximity;

            //Humidity sensor
            case 12:
                return humidity;

            //Rotation vector sensor
            case 20:
                return rotationVector;

            //Sensor not found
            default:
                return missingSensor;

        }
    }
}

