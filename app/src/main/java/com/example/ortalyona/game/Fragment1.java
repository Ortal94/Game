package com.example.ortalyona.game;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String ssid = getWifiName(getActivity().getApplicationContext());
        //TextView ssidTextView = (TextView) getActivity().findViewById(R.id.fragmentSSID);
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        TextView ssidTextView = (TextView) v.findViewById(R.id.fragmentSSID);
        ssidTextView.setText(ssid);


        // Inflate the layout for this fragment
        return v;
//                inflater.inflate(R.layout.fragment_fragment1, container, false);


    }
    public String getWifiName(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return wifiInfo.getSSID();
                }
            }
        }
        return null;
    }
}
