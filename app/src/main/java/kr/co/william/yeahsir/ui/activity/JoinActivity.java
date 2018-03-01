package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kr.co.william.yeahsir.R;

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
                break;
        }
    }
}
