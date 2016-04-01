package tr.com.orties.curse.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tr.com.orties.curse.R;
import tr.com.orties.curse.activities.MainActivity;
import tr.com.orties.curse.services.LocationService;
import tr.com.orties.curse.services.UserService;

public class TabFragment1 extends Fragment implements View.OnClickListener {

    EditText editText;
    Button button;
    String lastUserName;
    UserService userService;
    LocationService locationService;

    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        editText = (EditText) view.findViewById(R.id.etUserName);
        button = (Button) view.findViewById(R.id.submitUsernameButton);
        button.setOnClickListener(this);
        userService = new UserService(getActivity().getBaseContext());
        locationService = new LocationService(getActivity().getBaseContext());
        lastUserName = userService.retrieveLastUserName();
        if (lastUserName != null) {
            editText.setText(lastUserName);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        if(locationService.isGPSOn()) {
            locationService.startGettingLocation();
            String latitude = locationService.getLoc().getLatitude();
            String longitude = locationService.getLoc().getLatitude();
            Log.d("gelen değer lat: " , latitude);
            Log.d("gelen değer lng: ", longitude);
            if(latitude != "-1" && longitude != "-1") {
                ((MainActivity) getActivity()).updateMessageList();
                lastUserName = editText.getText().toString();
                userService.saveUserName(lastUserName);
                ((MainActivity) getActivity()).addTab("Conversation");
            }
        }else {
            Toast.makeText(this.getActivity().getBaseContext(), "GPS yok lan!!", Toast.LENGTH_SHORT).show();
        }
    }
}
