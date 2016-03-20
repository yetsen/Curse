package tr.com.orties.curse.services;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;


/**
 * Created by Yunus on 13-Mar-16.
 */
public class LocatifyRestService {
    private static final String BASE_URL = "http://37.139.15.209:8080/locatify/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void messageService(Context context, String url, HttpEntity entity, AsyncHttpResponseHandler responseHandler) {
        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
