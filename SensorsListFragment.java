package salma.mah.se.sensorslist;

/**
 * Created by Salma on 6/6/2017.
 */
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorsListFragment extends Fragment {

    private ListView listView;
    private View myView;

    SensorDataFragment sensorFragment = new SensorDataFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_sensorslist, container, false);

        setList();

        return myView;
    }

    /**
     * Creates the list containing all the sensors
     */
    public void setList(){

        listView = (ListView) myView.findViewById(R.id.List);

        //Creates new adapter for the listView
        AdapterItem adapter = new AdapterItem(getActivity(),  R.layout.list_view_row, getSensorList());
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                Sensor sensorClicked = (Sensor) listView.getItemAtPosition(position);

                //Bundle that contains the type of clicked sensor
                Bundle bundle = new Bundle();
                bundle.putInt("sensor", sensorClicked.getType());
                sensorFragment.setArguments(bundle);

                //Displays the SensorFragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main, sensorFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

    /**
     * Returns ArrayList which contains all the available sensors on the phone
     * @return
     */
    public ArrayList<Sensor> getSensorList(){

        SensorManager smm;
        List<Sensor> sensor;

        smm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = smm.getSensorList(Sensor.TYPE_ALL);

        ArrayList<Sensor> sensorList = new ArrayList<Sensor>(sensor.size());
        sensorList.addAll(sensor);

        return sensorList;
    }
}

