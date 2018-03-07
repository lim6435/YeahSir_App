package kr.co.william.yeahsir.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.MemberInfo;
import kr.co.william.yeahsir.ui.CompetitionListAdapter;

/**
 * Created by sheo on 2018-03-06.
 */

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.iv_toggle).setVisibility(View.GONE);

        ArrayList<String> testData = new ArrayList<>();
        testData.add("지성컵");
        testData.add("허솔컵");
        testData.add("2018 칠십리배 춘계 전국유소년축구연맹전");
        testData.add("2018 금석배 전국 초등학생 축구대회");
        testData.add("제54회 춘계한국고등학교축구연맹전");
        testData.add("제39회 대한축구협회장배 전국고등학교축구대회");
        testData.add("2018 KEB하나은행 FA CUP (2라운드)");

        CompetitionListAdapter competitionListAdapter = new CompetitionListAdapter(testData);
        RecyclerView rv_attend_list = (RecyclerView) findViewById(R.id.rv_competiti_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_attend_list.setLayoutManager(mLayoutManager);
        rv_attend_list.setAdapter(competitionListAdapter);
        rv_attend_list.setHasFixedSize(true);

        MemberInfo info = new MemberInfo.Builder().setHeight("").build();
    }
}
