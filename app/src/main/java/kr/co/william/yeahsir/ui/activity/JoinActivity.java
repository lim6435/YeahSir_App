package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.NetworkInfo;
import kr.co.william.yeahsir.network.HttpRequest;
import kr.co.william.yeahsir.network.NetworkCallback;
import kr.co.william.yeahsir.ui.PopupDialog;
import kr.co.william.yeahsir.utils.CommonUtil;

/**
 * Created by sheo on 2018-02-27.
 */

public class JoinActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);
        findViewById(R.id.bt_join).setOnClickListener(this);
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
        switch (v.getId()) {

            case R.id.bt_join:
                reqJoin();
                break;
        }
    }

    private void reqJoin() {
        HttpRequest.getInstance().sendData(this, NetworkInfo.URL_JOIN, getLoginInfo(), 0, callback);
    }

    private JSONObject getLoginInfo() {

        JSONObject joinInfo = new JSONObject();
//        if (!CommonUtil.isEmpty(et_id.getText().toString()) && !CommonUtil.isEmpty(et_pw.getText().toString())) {
            try {
                joinInfo.put("id", "sheo");
                joinInfo.put("pwd", "1234");
                joinInfo.put("memName", "허솔");
                joinInfo.put("lgnTpcd", "0");
                joinInfo.put("memBirth", "19861011");
                joinInfo.put("memTall", "170");
                joinInfo.put("memWeight", "100");

            } catch (JSONException e) {
                e.printStackTrace();
                joinInfo = null;
            }
//        } else {
//            loginInfo = null;
//        }
        System.out.println("[sheotest] getLoginInfo : " + joinInfo);
        return joinInfo;
    }

    private NetworkCallback callback = new NetworkCallback() {
        @Override
        public void onResponse(String responseData, int code) {
            System.out.println("[sheotest] 성공 responseData: " + responseData);
//            if (parseData(responseData)) {
//                moveMainMenu();
//            } else {
//                System.out.println("[sheotest] 데이터 응답오류");
//                PopupDialog.show(getApplicationContext(), "데이터 응답오류");
//            }
        }

        @Override
        public void onFailure(String msg, int code) {
            System.out.println("[sheotest] 실패 msg: " + msg);
        }
    };

}
