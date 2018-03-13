package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.CompetitionVo;

/**
 * Created by sheo on 2018-03-06.
 */

public class ApplyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);

        CompetitionVo info = (CompetitionVo) getIntent().getSerializableExtra("competi_info");
        System.out.println("[sheotest] name: "+ info.getName());
        System.out.println("[sheotest] type:"+ info.getType());

//        CompetitionListAdapter competitionListAdapter = new CompetitionListAdapter(getApplicationContext(), testData);
//        RecyclerView rv_attend_list = (RecyclerView) findViewById(R.id.rv_competi_list);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        rv_attend_list.setLayoutManager(mLayoutManager);
//        rv_attend_list.setAdapter(competitionListAdapter);
//        rv_attend_list.setHasFixedSize(true);
    }
}
