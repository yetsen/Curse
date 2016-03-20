package tr.com.orties.curse.services;

import android.content.Context;
import android.content.SharedPreferences;

import tr.com.orties.curse.activities.MainActivity;

/**
 * Created by Yunus on 20-Mar-16.
 */
public class UserService {

    Context context;

    public UserService(Context context) {
        this.context = context;
    }

    public String retrieveLastUserName() {
        SharedPreferences prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, context.MODE_PRIVATE);
        String restoredText = prefs.getString("userName", null);
        return restoredText;
    }


    public void saveUserName(String userName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MainActivity.PREFS_NAME, context.MODE_PRIVATE).edit();
        editor.putString("userName", userName);
        editor.commit();
    }
}
