package tr.com.orties.curse.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tr.com.orties.curse.R;
import tr.com.orties.curse.model.Message;

/**
 * Created by Yunus on 13-Mar-16.
 */
public class MessageListAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private List<Message> messageList;

    public MessageListAdapter(Activity activity, List<Message> messageList) {
        this.activity = activity;
        inflater = (LayoutInflater) this.activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.messageList=messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int i) {
        return messageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.message, null);
        TextView message = (TextView) view.findViewById(R.id.message_text);
        message.setText(messageList.get(i).getMessage());
        return view;
    }
}
