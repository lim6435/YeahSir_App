package kr.co.william.yeahsir.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.NetworkInfo;
import kr.co.william.yeahsir.network.HttpRequest;
import kr.co.william.yeahsir.network.NetworkCallback;
import kr.co.william.yeahsir.ui.PopupDialog;
import kr.co.william.yeahsir.ui.ProgressDialog;
import kr.co.william.yeahsir.utils.CommonUtil;

/**
 * Created by sheo on 2018-02-11.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_id;
    private EditText et_pw;

    // test
    Handler mHandler;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_join).setOnClickListener(this);

        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.bt_login:

                if (getLoginInfo() == null) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setTitle("알림").setMessage("ID / Password를 정확히 입력해 주세요.").setPositiveButton("확인", null);
                    AlertDialog dialog = alertDialogBuilder.create();
                    dialog.show();
                } else {
                    reqLogin();
                }

                break;

            case R.id.bt_join:
                intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                break;
        }
    }

    private JSONObject getLoginInfo() {

        JSONObject loginInfo = new JSONObject();
        if (!CommonUtil.isEmpty(et_id.getText().toString()) && !CommonUtil.isEmpty(et_pw.getText().toString())) {
            try {
                loginInfo.put("id", et_id.getText().toString());
                loginInfo.put("pwd", et_pw.getText().toString());

                /* 테스트소스
                JSONArray test = new JSONArray();
                test.put("aaa");
                test.put("bbb");
                test.put("ccc");
                loginInfo.put("test", test);

                JSONObject a1 = new JSONObject();
                a1.put("key1", "111");
                a1.put("key2", "222");
                a1.put("key3", "333");
                a1.put("key4", "444");

                JSONObject a2 = new JSONObject();
                a2.put("key1", "111");
                a2.put("key2", "222");
                a2.put("key3", "333");
                a2.put("key4", "444");

                JSONArray j1 = new JSONArray();
                j1.put(a1);
                j1.put(a2);

                loginInfo.put("test", test);
                loginInfo.put("o1", a1);
                loginInfo.put("o2", a2);
                loginInfo.put("j1", j1); */

            } catch (JSONException e) {
                e.printStackTrace();
                loginInfo = null;
            }
        } else {
            loginInfo = null;
        }
        System.out.println("[sheotest] getLoginInfo : " + loginInfo);
        return loginInfo;
    }

    private void reqLogin() {
        HttpRequest.getInstance().sendData(this, NetworkInfo.URL_LOGIN, getLoginInfo(), 0, callback);
    }

    private NetworkCallback callback = new NetworkCallback() {
        @Override
        public void onResponse(String responseData, int code) {
            System.out.println("[sheotest] 성공 responseData: " + responseData);
            if (parseData(responseData)) {
                moveMainMenu();
            } else {
                System.out.println("[sheotest] 데이터 응답오류");
                PopupDialog.show(getApplicationContext(), "데이터 응답오류");
            }
        }

        @Override
        public void onFailure(String msg, int code) {
            System.out.println("[sheotest] 실패 msg: " + msg);
        }
    };

    private boolean parseData(String responseData) {
        try {
            JSONObject json = new JSONObject(responseData);
            String memName = json.getString("memName");
            String memId = json.getString("memId");
            String memBirth = json.getString("memBirth");
            String memTall = json.getString("memTall");
            String memWeight = json.getString("memWeight");
            JSONArray getCoptInfo = json.getJSONArray("getCoptList");

            System.out.println("[sheotest] memName: " + memName);
            System.out.println("[sheotest] memId: " + memId);
            System.out.println("[sheotest] memBirth: " + memBirth);
            System.out.println("[sheotest] memTall: " + memTall);
            System.out.println("[sheotest] memWeight: " + memWeight);
            System.out.println("[sheotest] getCoptInfo len: " + getCoptInfo.length());

            for (int i = 0; i < getCoptInfo.length(); i++) {
                JSONObject object = getCoptInfo.getJSONObject(i);
                String coptDt = object.getString("coptDt");
                String coptAgeGrop = object.getString("coptAgeGrop");
                String coptPrid = object.getString("coptPrid");

                System.out.println("[sheotest] coptDt: " + coptDt);
                System.out.println("[sheotest] coptAgeGrop: " + coptAgeGrop);
                System.out.println("[sheotest] coptPrid: " + coptPrid);
            }
            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("[sheotest] json 파싱오류");
            return true; // sheotest TODO 테스트코드임 지우기
        }
//        return false;
    }

    private void moveMainMenu() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
