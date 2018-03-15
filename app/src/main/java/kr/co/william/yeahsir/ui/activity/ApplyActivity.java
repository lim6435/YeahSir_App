package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.CompetitionVo;
import kr.co.william.yeahsir.data.MemberVo;
import kr.co.william.yeahsir.ui.adapter.ApplyMemberListAdapter;

/**
 * Created by sheo on 2018-03-06.
 */

public class ApplyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);

        CompetitionVo info = (CompetitionVo) getIntent().getSerializableExtra("competition_info");
        System.out.println("[sheotest] name: " + info.getName());
        System.out.println("[sheotest] type:" + info.getType());

        int maxMemberCount = 12;
        ArrayList<MemberVo> testData = new ArrayList<>();
        for (int i = 0; i < maxMemberCount; i++) {
            testData.add(new MemberVo.Builder().build());
        }

        ApplyMemberListAdapter applyMemberListAdapter = new ApplyMemberListAdapter(getApplicationContext(), testData);
        RecyclerView rv_attend_list = (RecyclerView) findViewById(R.id.rv_member_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_attend_list.setLayoutManager(mLayoutManager);
        rv_attend_list.setAdapter(applyMemberListAdapter);
//        rv_attend_list.setHasFixedSize(true);
    }
}
