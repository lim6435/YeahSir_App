package kr.co.william.yeahsir.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.HttpURLConnection;

import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.network.HttpRequest;
import kr.co.william.yeahsir.network.NetworkCallback;

/**
 * Created by sheo on 2018-02-11.
 */

public class MainFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpRequest.getInstance().sendData(getActivity(), "url", "msg", 0, new NetworkCallback() {
            @Override
            public void onResponse(String responseData, int code) {
                System.out.println("[sheotest] 성공 responseData: " + responseData);
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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
