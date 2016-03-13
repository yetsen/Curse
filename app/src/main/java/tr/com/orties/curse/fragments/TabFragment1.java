package tr.com.orties.curse.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tr.com.orties.curse.R;
import tr.com.orties.curse.adapters.MessageListAdapter;

public class TabFragment1 extends Fragment {

    View view;
    ListView lv;

    public TabFragment1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        lv = (ListView) view.findViewById(R.id.messagesListView);
        MessageListAdapter adapter = new MessageListAdapter();
        lv.setAdapter(adapter);
        return view;
    }
}
