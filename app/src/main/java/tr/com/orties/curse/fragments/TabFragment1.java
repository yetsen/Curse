package tr.com.orties.curse.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tr.com.orties.curse.R;
import tr.com.orties.curse.activities.MainActivity;
import tr.com.orties.curse.services.UserService;

public class TabFragment1 extends Fragment implements View.OnClickListener {

    EditText editText;
    Button button;
    String lastUserName;
    UserService userService;

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
        lastUserName = userService.retrieveLastUserName();
        if (lastUserName != null) {
            editText.setText(lastUserName);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        lastUserName = editText.getText().toString();
        userService.saveUserName(lastUserName);
        ((MainActivity) getActivity()).addTab("Conversation");
    }
}
