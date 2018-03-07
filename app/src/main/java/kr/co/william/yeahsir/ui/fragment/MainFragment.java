package kr.co.william.yeahsir.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.network.HttpRequest;
import kr.co.william.yeahsir.network.NetworkCallback;
import kr.co.william.yeahsir.ui.AttendListAdapter;
import kr.co.william.yeahsir.ui.activity.SearchActivity;

/**
 * Created by sheo on 2018-02-11.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener {

    private final String URL = "http://ec2-13-125-105-147.ap-northeast-2.compute.amazonaws.com:8080/test";
//    private final String URL = "http://mw.vpay.co.kr/event/createApp.jsp";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 테스트중
        JSONObject testInfo = new JSONObject();
        try {
            testInfo.put("phoneNum", "01032533179");
            testInfo.put("name", "허솔");
            testInfo.put("address", "삼성제일빌딩 10F");
            testInfo.put("test", "test");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("[sheotest] send data: " + testInfo.toString());
        HttpRequest.getInstance().sendData(getActivity(), URL, testInfo, 0, new NetworkCallback() {
            @Override
            public void onResponse(String responseData, int code) {
                System.out.println("[sheotest] 성공 responseData: " + responseData);

                try {
                    JSONObject json = new JSONObject(responseData);
                    String mem_name = json.getString("mem_name");
                    String mem_id = json.getString("mem_id");

                    System.out.println("[sheotest] mem_name: " + mem_name);
                    System.out.println("[sheotest] mem_id: " + mem_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("[sheotest] json 파싱오류");
                }
            }

            @Override
            public void onFailure(String msg, int code) {
                System.out.println("[sheotest] 실패 msg: " + msg);
            }
        });
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
