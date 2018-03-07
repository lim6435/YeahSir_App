package kr.co.william.yeahsir.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.IntentCode;
import kr.co.william.yeahsir.ui.fragment.TestFragment;
import kr.co.william.yeahsir.utils.CommonUtil;

/**
 * Created by sheo on 2018-02-11.
 */

public class SubActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        String menuType = "";
        Intent intent = getIntent();
        if (intent != null) {
            menuType = intent.getStringExtra(IntentCode.INTENT_KEY_MENU_TYPE);
        }

        CommonUtil.callFragment(SubActivity.this, new TestFragment(), null, true);
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
}
