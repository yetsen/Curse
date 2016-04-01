package tr.com.orties.curse.services;

import android.content.Context;
import android.content.SharedPreferences;

import tr.com.orties.curse.activities.MainActivity;

/**
 * Created by Yunus on 01-Apr-16.
 */
public class MessageService {

    Context context;

    public MessageService(Context context) {
        this.context = context;
    }

    public int retrieveLastMessageId() {
        SharedPreferences prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, context.MODE_PRIVATE);
        int restoredMessageId = prefs.getInt("messageId", 0);
        return restoredMessageId;
    }


    public void saveMessageId(int messageId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MainActivity.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putInt("messageId", messageId);
        editor.commit();
    }

}

