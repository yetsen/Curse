package tr.com.orties.curse.services;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import tr.com.orties.curse.model.Location;
import tr.com.orties.curse.model.Message;

/**
 * Created by Yunus on 13-Mar-16.
 */
public class LocatifyRestServiceUsage {

    List<Message> messageList = new ArrayList<Message>();

    public List<Message> getMessages(final Context context, String latitude, String longitude, int lastId){
        JSONObject jsonParams = new JSONObject();
        try {
            JSONObject point = new JSONObject();
            point.put("latitude",latitude);
            point.put("longitude", longitude);
            jsonParams.put("point", point);
            jsonParams.put("lastId", lastId);
            LocatifyRestService.messageService(context, "curse/getMessages", new StringEntity(jsonParams.toString()), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.d("response : ", response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            Message message = new Message();
                            JSONObject object = response.getJSONObject(i);
                            message.setMessage(object.getString("message"));
                            message.setUsername(object.getString("username"));
                            Location location = new Location();
                            location.setLatitude(object.getString("latitude"));
                            location.setLongitude(object.getString("longitude"));
                            message.setLocation(location);
                            message.setId(object.getInt("id"));
                            messageList.add(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    super.onSuccess(statusCode, headers, response);
                }
            });
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    public void sendMessage(final Context context, Message message) {
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("message", message.getMessage());
            jsonParams.put("latitude", message.getLocation().getLatitude());
            jsonParams.put("longitude", message.getLocation().getLongitude());
            jsonParams.put("username", message.getUsername());
            LocatifyRestService.messageService(context, "curse/sendMessage", new StringEntity(jsonParams.toString()), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.d("response : ", response.toString());
                    super.onSuccess(statusCode, headers, response);
                }
            });
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
