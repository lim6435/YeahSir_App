package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.CompetitionVo;
import kr.co.william.yeahsir.ui.adapter.CompetitionListAdapter;

/**
 * Created by sheo on 2018-03-06.
 */

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);

        ArrayList<CompetitionVo> testData = new ArrayList<>();
        testData.add(new CompetitionVo.Builder().setName("허솔컵").build());
        testData.add(new CompetitionVo.Builder().setName("2018 칠십리배 춘계 전국유소년축구연맹전").build());
        testData.add(new CompetitionVo.Builder().setName("2018 금석배 전국 초등학생 축구대회").build());
        testData.add(new CompetitionVo.Builder().setName("제54회 춘계한국고등학교축구연맹전").build());
        testData.add(new CompetitionVo.Builder().setName("제39회 대한축구협회장배 전국고등학교축구대회").build());
        testData.add(new CompetitionVo.Builder().setName("2018 KEB하나은행 FA CUP (2라운드)").build());

        CompetitionListAdapter competitionListAdapter = new CompetitionListAdapter(getApplicationContext(), testData);
        RecyclerView rv_attend_list = (RecyclerView) findViewById(R.id.rv_competi_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_attend_list.setLayoutManager(mLayoutManager);
        rv_attend_list.setAdapter(competitionListAdapter);
        rv_attend_list.setHasFixedSize(true);
    }
}
