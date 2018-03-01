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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

    // sheotest TODO
    private boolean checkError(String responseData) {
        if (false) {
            return true;
        } else {
            return false;
        }
    }

    public String send(String sUrl, String msg) {
        String result = "";

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

            ////////////////////////////////////////////////////////

            JSONObject testInfo = new JSONObject();
            testInfo.put("phoneNum", "01032533179");
            testInfo.put("name", "허솔");
            testInfo.put("address", "삼성제일빌딩 10F");
            testInfo.put("test", "test");

            os = conn.getOutputStream();
            os.write(testInfo.toString().getBytes("EUC-KR"));
            os.flush();

            /////////////////////////////////////////////////////

//            //--------------------------
//            //   서버로 값 전송
//            //--------------------------
//            StringBuffer buffer = new StringBuffer();
//            buffer.append(msg);
//
//            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "EUC-KR");
//            PrintWriter writer = new PrintWriter(outStream);
//            writer.write(buffer.toString());
//            writer.flush();

            /////////////////////////////////////////////////

            String response;
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


                //////////////////////////////////////////////////////////////////

//                //--------------------------
//                //   서버에서 전송받기
//                //--------------------------
//                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
//                BufferedReader reader = new BufferedReader(tmp);
//                StringBuilder builder = new StringBuilder();
//                String str;
//                while ((str = reader.readLine()) != null) { // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
//                    builder.append(str + "\n"); // View에 표시하기 위해 라인 구분자 추가
//                }
//                result = builder.toString(); // 전송결과를 전역 변수에 저장

            } else {
                System.out.println("[sheotest] error !!!!!!!!!!!!!!!");
                result = "";
            }

        } catch (MalformedURLException e) {
            System.out.println("[sheotest] error MalformedURLException");
            e.printStackTrace();
            result = "";
        } catch (IOException e) {
            System.out.println("[sheotest] error IOException");
            e.printStackTrace();
            result = "";
        } catch (Exception e) {
            System.out.println("[sheotest] error Exception");
            e.printStackTrace();
            result = "";
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            return result;
        }
    }

    public String test(String url, String msg) {
        String result = "";

        try {
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL ConnectionUrl = new URL(url); // URL 설정
            HttpURLConnection conn = (HttpURLConnection) ConnectionUrl.openConnection(); // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true); // 서버에서 읽기 모드 지정
            conn.setDoOutput(true); // 서버로 쓰기 모드 지정
            conn.setRequestMethod("POST"); // 전송 방식은 POST
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            buffer.append(msg);

            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();


            int responseCode = conn.getResponseCode();
            System.out.println("[sheotest] responseCode: " + responseCode);

            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) { // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n"); // View에 표시하기 위해 라인 구분자 추가
            }
            result = builder.toString(); // 전송결과를 전역 변수에 저장

        } catch (MalformedURLException e) {
            System.out.println("[sheotest] error MalformedURLException");
            e.printStackTrace();
            result = "";
        } catch (IOException e) {
            System.out.println("[sheotest] error IOException");
            e.printStackTrace();
            result = "";
        } catch (Exception e) {
            System.out.println("[sheotest] error Exception");
            e.printStackTrace();
            result = "";
        } finally {
            System.out.println("[sheotest] result = " + result);
            return result;
        }
    }
}
