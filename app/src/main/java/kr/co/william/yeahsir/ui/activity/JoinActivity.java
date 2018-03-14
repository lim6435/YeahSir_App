package kr.co.william.yeahsir.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

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

    //Variable
    //EditText
    // member Id
    private EditText et_id;
    // memver password
    private EditText et_pw;
    // member name
    private EditText et_name;
    // member birthday
    private EditText et_birth;
    // member tall
    private EditText et_height;
    // member weight
    private EditText et_weight;

    //Override function
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);
        findViewById(R.id.bt_join).setOnClickListener(this);

        //EditText Init
        et_id = (EditText)findViewById(R.id.et_id);             // id
        et_pw = (EditText)findViewById(R.id.et_pw);             // password
        et_name = (EditText)findViewById(R.id.et_name);         // name
        et_birth = (EditText)findViewById(R.id.et_birth);       // birthday
        et_height = (EditText)findViewById(R.id.et_height);     // tall
        et_weight = (EditText)findViewById(R.id.et_weight);     // weight
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
                if (getJoinInfo() == null) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(JoinActivity.this);
                    alertDialogBuilder.setTitle("알림").setMessage("가입 정보를 정확히 입력해 주세요.").setPositiveButton("확인", null);
                    AlertDialog dialog = alertDialogBuilder.create();
                    dialog.show();
                } else {
                    reqJoin();
                }
                break;
        }
    }

    private void reqJoin() {
        HttpRequest.getInstance().sendData(this, NetworkInfo.URL_JOIN, getJoinInfo(), 0, callback);
    }

    private JSONObject getJoinInfo() {

        JSONObject joinInfo = new JSONObject();
        if (!CommonUtil.isEmpty(et_id.getText().toString()) && !CommonUtil.isEmpty(et_pw.getText().toString())) {
            try {
                joinInfo.put("id", et_id.getText().toString());
                joinInfo.put("pwd", et_pw.getText().toString());
                joinInfo.put("memName", URLEncoder.encode(et_name.getText().toString()));
                joinInfo.put("lgnTpcd", "0");
                joinInfo.put("memBirth", et_birth.getText().toString());
                joinInfo.put("memTall", et_height.getText().toString());
                joinInfo.put("memWeight", et_weight.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
                joinInfo = null;
            }
        } else {
            joinInfo = null;
        }
        System.out.println("[sheotest] getJoinInfo : " + joinInfo);
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
