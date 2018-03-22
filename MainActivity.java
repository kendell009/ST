package salma.mah.se.sensorslist;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //List for all the sensors
    SensorsListFragment list = new SensorsListFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Displays ListFragment
        FragmentTransaction fragtransaction = getSupportFragmentManager().beginTransaction();
        fragtransaction.replace(R.id.activity_main, list);

        fragtransaction.commit();
    }
}
