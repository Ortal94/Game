package com.example.ortalyona.game;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment implements LocationListener {

//    private TextView textView;
    TextView GPSTextView;

    private LocationManager locationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        textView = (TextView) getView().findViewById(R.id.fragmentGPS);
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        GPSTextView = (TextView) v.findViewById(R.id.fragmentGPS);
       // GPSTextView.setText(ssid);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
        // Inflate the layout for this fragment
        return v;
                //inflater.inflate(R.layout.fragment_fragment2, container, false);
    }
    @Override
    public void onLocationChanged(Location location) {

        double longtitude = location.getLongitude();
        double latitude = location.getLatitude();
        GPSTextView.setText("Longtitude: "+longtitude+", "+"Latitude: "+latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
