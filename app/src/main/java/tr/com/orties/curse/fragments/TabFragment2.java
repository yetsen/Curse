package tr.com.orties.curse.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import tr.com.orties.curse.R;
import tr.com.orties.curse.adapters.MessageListAdapter;
import tr.com.orties.curse.model.Location;
import tr.com.orties.curse.model.Message;
import tr.com.orties.curse.services.LocatifyRestServiceUsage;

public class TabFragment2 extends Fragment implements View.OnClickListener{

    View view;
    ListView lv;
    ImageView imageView;
    EditText editText;
    List<Message> messageList;
    MessageListAdapter adapter;

    LocatifyRestServiceUsage restServiceUsage = new LocatifyRestServiceUsage();

    public TabFragment2() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
        lv = (ListView) view.findViewById(R.id.messagesListView);
        imageView = (ImageView) view.findViewById(R.id.imageSend);
        editText = (EditText) view.findViewById(R.id.messageText);
        imageView.setOnClickListener(this);
        messageList = restServiceUsage.getMessages(getActivity(), "41.1875758", "29.2443909");
        adapter = new MessageListAdapter(getActivity(), messageList);
        lv.setAdapter(adapter);
        scrollMyListViewToBottom();
        return view;
    }

    @Override
    public void onClick(View view) {
        Message message = new Message();
        Location location = new Location();
        location.setLatitude("41.1875758");
        location.setLongitude("29.2443909");
        message.setLocation(location);
        message.setMessage(editText.getText().toString());
        message.setUsername("yunus");
        restServiceUsage.sendMessage(getActivity(), message);
        editText.setText(null);
        messageList.add(message);
        scrollMyListViewToBottom();
    }

    private void scrollMyListViewToBottom() {
        lv.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lv.setSelection(adapter.getCount() - 1);
            }
        });
    }

}
