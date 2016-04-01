package tr.com.orties.curse.services;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Yunus on 20-Mar-16.
 */
public class LocationService implements LocationListener{

    Context context;
    LocationManager manager;
    tr.com.orties.curse.model.Location loc;

    public LocationService(Context context) {
        this.context = context;
        this.manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        loc = new tr.com.orties.curse.model.Location();
        loc.setLongitude("-1");
        loc.setLatitude("-1");
    }


    public tr.com.orties.curse.model.Location getLoc() {
        return loc;
    }

    public void setLoc(tr.com.orties.curse.model.Location loc) {
        this.loc = loc;
    }

    public boolean isGPSOn() {
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return statusOfGPS;
    }

    public void startGettingLocation() {
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("latitude : ", location.getLatitude() + "");
        Log.d("longitude : ", location.getLongitude() + "");
        loc.setLatitude(location.getLatitude() + "");
        loc.setLongitude(location.getLongitude() + "");
        //updateMessageList();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
