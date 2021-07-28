package com.android.trovi.BackgroundCollect;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.Locale;

public class LocationCollect {

    double longitude = 0.0;
    double latitude = 0.0;

    public void collectLatitude(Context context){
        Location gps_loc;
        Location network_loc;
        Location final_loc;

        String userCountry, userAddress, testeKind;

        //LATLONG
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        try {

            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (gps_loc != null) {
                final_loc = gps_loc;
                latitude = final_loc.getLatitude();
                longitude = final_loc.getLongitude();
            }
            else if (network_loc != null) {
                final_loc = network_loc;
                latitude = final_loc.getLatitude();
                longitude = final_loc.getLongitude();
            }
            else {
                latitude = 0.0;
                longitude = 0.0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            //latitude = Double.parseDouble(Common.LAT);
            //longitude = Double.parseDouble(Common.LONG);

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                userCountry = addresses.get(0).getCountryName();
                userAddress = addresses.get(0).getAddressLine(0);
                testeKind = addresses.get(0).getAdminArea();
                //tv.setText(userCountry + ", " + userAddress);
            }
            else {
                userCountry = "Unknown";
                //tv.setText(userCountry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLatitude(){
        return Double.toString(latitude);
    }

    public String getLongitude(){
        return Double.toString(longitude);
    }

}
