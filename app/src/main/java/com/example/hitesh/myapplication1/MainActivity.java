package com.example.hitesh.myapplication1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText user_name;
    EditText address;
    EditText phone_number;
    Switch report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = findViewById(R.id.locationET);
        user_name = findViewById(R.id.nameET);
        phone_number = findViewById(R.id.phoneET);


    }

    LocationManager lm;

    public void submitData(View view){
        JSONObject userdata = new JSONObject();
        try {
            userdata.put("name", user_name.getText().toString());
            userdata.put("address", address.getText().toString());
            userdata.put("phone_number", phone_number.getText().toString());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String data_user = userdata.toString();


    }


    public void onClickLocation(View view) {


        Location myLocation = getLastKnownLocation();
        double longitude = myLocation.getLongitude();
        double latitude = myLocation.getLatitude();

        String finalLocation = Double.toString(latitude) + '#' + Double.toString(longitude);


        address.setText(finalLocation);


    }


    private Location getLastKnownLocation() {

        lm = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }else{
                Location l = lm.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }

        }
        return bestLocation;
    }



}
