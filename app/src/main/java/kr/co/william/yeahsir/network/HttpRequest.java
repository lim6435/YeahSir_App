package kr.co.william.yeahsir.network;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kr.co.william.yeahsir.utils.Logger;

/**
 * Created by sheo on 2018-02-28.
 */

public class HttpRequest {

    private final String TAG = "HttpRequest";
    private final int TIMEOUT = 20;

    private static HttpRequest httpRequest = null;
    private NetworkCallback callback;
    private int code;

    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            httpRequest = new HttpRequest();
        }
        return httpRequest;
    }

    public void sendData(Context context, String url, String msg, int code, NetworkCallback callback) {
        System.out.println("[sheotest] url: " + url);
        System.out.println("[sheotest] msg: " + msg);
        System.out.println("[sheotest] code: " + code);

        this.code = code;
        this.callback = callback;
        new ReqAsyncTask().execute(url, msg);
    }

    private class ReqAsyncTask extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object[] params) {

            String sUrl = (String) params[0];
            String msg = (String) params[1];
//            JSONObject test = (JSONObject) params[1];
            System.out.println("[sheotest] url: " + sUrl);
            System.out.println("[sheotest] msg: " + msg);

            HttpURLConnection conn = null;
            OutputStream os = null;
            InputStream is = null;
            ByteArrayOutputStream baos = null;

            try {
                URL url = new URL(sUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(TIMEOUT * 1000);
                conn.setReadTimeout(TIMEOUT * 1000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                JSONObject testInfo = new JSONObject();
                testInfo.put("phoneNum", "01032533179");
                testInfo.put("name", "허솔");
                testInfo.put("address", "삼성제일빌딩 10F");
                testInfo.put("test", "test");

                os = conn.getOutputStream();
                os.write(testInfo.toString().getBytes());
                os.flush();
                os.close();

                String response;
                int responseCode = conn.getResponseCode();
                System.out.println("[sheotest] responseCode: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    is = conn.getInputStream();
                    baos = new ByteArrayOutputStream();
                    byte[] byteBuffer = new byte[1024];
                    byte[] byteData = null;
                    int nLength = 0;
                    while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                        baos.write(byteBuffer, 0, nLength);
                    }
                    byteData = baos.toByteArray();
                    response = new String(byteData);

                    JSONObject responseJSON = new JSONObject(response);
                    Boolean result = (Boolean) responseJSON.get("result");
                    String age = (String) responseJSON.get("age");
                    String job = (String) responseJSON.get("job");

                    System.out.println("[sheotest] response: " + response);

                    /////////////////////////////////////////////////////////////////

//                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                    StringBuilder stringbuilder = new StringBuilder();
//                    String currentline = null;
//                    while ((currentline = br.readLine()) != null) {
//                        stringbuilder.append(currentline + "\n");
//                    }
//                    br.close();
//                    System.out.println("[sheotest] response: " + stringbuilder.toString());

                } else {
                    System.out.println("[sheotest] error !!!!!!!!!!!!!!!");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("[sheotest] error MalformedURLException");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[sheotest] error IOException");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[sheotest] error Exception");
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                return "test";
            }

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

    // sheotest TODO
    private boolean checkError(String responseData) {
        if (false) {
            return true;
        } else {
            return false;
        }
    }
}
