package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.CompetitionVo;
import kr.co.william.yeahsir.data.MemberVo;
import kr.co.william.yeahsir.ui.adapter.ApplyMemberListAdapter;

/**
 * Created by sheo on 2018-03-06.
 */

public class ApplyActivity extends BaseActivity {

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<EditText> editTexts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);

        CompetitionVo info = (CompetitionVo) getIntent().getSerializableExtra("competition_info");
        System.out.println("[sheotest] name: " + info.getName());
        System.out.println("[sheotest] type:" + info.getType());

        int maxMemberCount = 12;
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout ll_applyMemList = (LinearLayout) findViewById(R.id.ll_applyMemList);

        for (int i = 0; i < maxMemberCount; i++) {
            layoutInflater.inflate(R.layout.item_apply_list_view, ll_applyMemList);

            CheckBox cb_id = (CheckBox) ll_applyMemList.getChildAt(i).findViewById(R.id.cb_id);
            cb_id.setTag(i);
            cb_id.setOnCheckedChangeListener(checkedChangeListener);

            EditText et_id = (EditText) ll_applyMemList.getChildAt(i).findViewById(R.id.et_id);
            et_id.setTag(i);

            checkBoxes.add(cb_id);
            editTexts.add(et_id);
        }

//        ArrayList<MemberVo> testData = new ArrayList<>();
//        for (int i = 0; i < maxMemberCount; i++) {
//            testData.add(new MemberVo.Builder().build());
//        }
//        ApplyMemberListAdapter applyMemberListAdapter = new ApplyMemberListAdapter(getApplicationContext(), testData);
//        RecyclerView rv_attend_list = (RecyclerView) findViewById(R.id.rv_member_list);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        rv_attend_list.setLayoutManager(mLayoutManager);
//        rv_attend_list.setAdapter(applyMemberListAdapter);

    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.out.println("[sheotest] tag: " + buttonView.getTag());
            System.out.println("[sheotest] cb_id: " + buttonView + ", onCheckedChanged isChecked: " + isChecked);

            editTexts.get((int) buttonView.getTag()).setEnabled(isChecked);
//            if (isChecked) {
//                editTexts.get((int) buttonView.getTag()).setText("체크함");
//            } else {
//                editTexts.get((int) buttonView.getTag()).setText("체크안함");
//            }
        }
    };

}
