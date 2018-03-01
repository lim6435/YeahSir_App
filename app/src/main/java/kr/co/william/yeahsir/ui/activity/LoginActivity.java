package kr.co.william.yeahsir.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kr.co.william.yeahsir.R;

/**
 * Created by sheo on 2018-02-11.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);
        findViewById(R.id.bt_login).setOnClickListener(this);
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
        Intent intent;

        switch (v.getId()) {
            case R.id.bt_login:
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.bt_join:
                intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                break;
        }
    }
}
