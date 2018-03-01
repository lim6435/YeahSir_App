package kr.co.william.yeahsir.network;

import org.json.JSONObject;

/**
 * Created by sheo on 2018-02-28.
 */

public interface NetworkCallback {
    void onResponse(String responseData, int code);

    void onFailure(String msg, int code);
}
