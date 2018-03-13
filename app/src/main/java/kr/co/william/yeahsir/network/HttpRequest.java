package kr.co.william.yeahsir.network;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kr.co.william.yeahsir.data.NetworkInfo;

/**
 * Created by sheo on 2018-02-28.
 */

public class HttpRequest {

    private final String TAG = "HttpRequest";
    private final int TIMEOUT_SEC = 20;

    private static HttpRequest httpRequest = null;
    private NetworkCallback callback;
    private int code;

    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            httpRequest = new HttpRequest();
        }
        return httpRequest;
    }

    public void sendData(Context context, String url, JSONObject msg, int code, NetworkCallback callback) {
        System.out.println("[sheotest] url: " + url);
        System.out.println("[sheotest] msg: " + msg);
        System.out.println("[sheotest] code: " + code);

        this.code = code;
        this.callback = callback;
        new SendAsyncTask().execute(url, msg);
    }

    private class SendAsyncTask extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object[] params) {

            String sUrl = (String) params[0];
            JSONObject msg = (JSONObject) params[1];
            return send(sUrl, msg).trim();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String responseData) {
            System.out.println("[sheotest] responseData: " + responseData);

            if (checkError(responseData)) {
                callback.onFailure(responseData, code);
            } else {
                callback.onResponse(responseData, code);
            }
        }
    }

    private boolean checkError(String responseData) {
        if (NetworkInfo.NETWORK_FAIL_URLEXCEPTION.equals(responseData)
                || NetworkInfo.NETWORK_FAIL_IOEXCEPTION.equals(responseData)
                || NetworkInfo.NETWORK_FAIL_EXCEPTION.equals(responseData)
                || NetworkInfo.NETWORK_FAIL_RESPONSE.equals(responseData)) {
            return true;
        } else {
            return false;
        }
    }

    public String send(String sUrl, JSONObject msg) {

        HttpURLConnection conn = null;
        OutputStream os = null;
        String result = "";

        try {

            URL url = new URL(sUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIMEOUT_SEC * 1000);
            conn.setReadTimeout(TIMEOUT_SEC * 1000);
//            conn.setConnectTimeout(10);
//            conn.setReadTimeout(10);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            os = conn.getOutputStream();
            os.write(msg.toString().getBytes("EUC-KR"));
            os.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("[sheotest] responseCode: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringbuilder = new StringBuilder();
                String currentline = null;
                while ((currentline = br.readLine()) != null) {
                    stringbuilder.append(currentline + "\n");
                }

                result = stringbuilder.toString();
                System.out.println("[sheotest] response: " + result);

            } else {
                result = NetworkInfo.NETWORK_FAIL_RESPONSE;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = NetworkInfo.NETWORK_FAIL_URLEXCEPTION;
        } catch (IOException e) {
            e.printStackTrace();
            result = NetworkInfo.NETWORK_FAIL_IOEXCEPTION;
        } catch (Exception e) {
            e.printStackTrace();
            result = NetworkInfo.NETWORK_FAIL_EXCEPTION;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            return result;
        }
    }

}
