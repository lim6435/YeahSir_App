package kr.co.william.yeahsir.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.ui.adapter.AttendListAdapter;
import kr.co.william.yeahsir.ui.activity.SearchActivity;

/**
 * Created by sheo on 2018-02-11.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<String> testData = new ArrayList<>();
        testData.add("지성컵");
        testData.add("허솔컵");
        testData.add("2018 칠십리배 춘계 전국유소년축구연맹전");
        testData.add("2018 금석배 전국 초등학생 축구대회");
        testData.add("제54회 춘계한국고등학교축구연맹전");
        testData.add("제39회 대한축구협회장배 전국고등학교축구대회");
        testData.add("2018 KEB하나은행 FA CUP (2라운드)");

        AttendListAdapter attendListAdapter = new AttendListAdapter(testData);
        RecyclerView rv_attend_list = (RecyclerView) view.findViewById(R.id.rv_attend_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_attend_list.setLayoutManager(mLayoutManager);
        rv_attend_list.setAdapter(attendListAdapter);
        rv_attend_list.setHasFixedSize(true);
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        rv_attend_list.addItemDecoration(itemDecoration);

        view.findViewById(R.id.et_search).setOnClickListener(this);

//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_photo);
//        Glide.with(this).load("http://m.bccard.com/img/mobilecard/1119705/50_default.png")
//                .override(CommonUtil.getImageWidth(getActivity(), R.drawable.ic_account_circle_black), CommonUtil.getImageHeight(getActivity(), R.drawable.ic_account_circle_black))
//                .into(imageView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                System.out.println("[sheotest] 대회검색 클릭");
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);

                break;
        }
    }
}
