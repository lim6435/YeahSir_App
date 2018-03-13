package kr.co.william.yeahsir.utils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.co.william.yeahsir.AppInfo;
import kr.co.william.yeahsir.R;

/**
 * Created by sheo on 2018-02-08.
 */

public class CommonUtil {

    private final static String TAG = CommonUtil.class.getSimpleName();

    public static void callFragment(final FragmentActivity fragmentAcitivity, final Fragment fragment, final Bundle bundle, final boolean isSelfStart) {

        FragmentManager fragmentManager = fragmentAcitivity.getSupportFragmentManager();

        if (fragment != null) {
            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            try {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (isSelfStart) {
                    fragmentTransaction.add(R.id.container, fragment);
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        Logger.i(TAG, "callFragment() selfStart commitNow");
                        fragmentTransaction.commitNow();
                    } else {
                        Logger.i(TAG, "callFragment() selfStart executePendingTransactions");
                        fragmentTransaction.commit();
                        fragmentManager.executePendingTransactions();
                    }
                } else {
                    fragmentTransaction.replace(R.id.container, fragment);
                    Logger.i(TAG, "callFragment commit");
                    fragmentTransaction.commit();
                }

                Logger.i(TAG, "callFragment() finish");
            } catch (IllegalStateException e) {
                if (!fragmentAcitivity.isFinishing()) {
                    Logger.i(TAG, "callFragment() IllegalStateException");
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
                    printStackTrace(e);
                }
            }
        }
    }

    public static void addFragment(FragmentActivity fragmentActivity, Fragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        if (fragment != null) {
            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            try {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container, fragment);
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commitAllowingStateLoss();
            } catch (IllegalStateException e) {
                printStackTrace(e);
            }
        }
    }

    protected void goBack(FragmentActivity acitivity) {
        Logger.i(TAG, "goBack() pressed");

//        FragmentManager fm = fragment.getFragmentManager();
        FragmentManager fm = acitivity.getSupportFragmentManager();
        Logger.i(TAG, "goBack() fm.getBackStackEntryCount() : " + fm.getBackStackEntryCount());

        // fragment 스택이 남아 있으면 이전 화면 fragment 로 이동
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
//            activity.finish();
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()))
            return true;
        else
            return false;
    }

    public static void printStackTrace(Exception e) {
        if (AppInfo.getInstance().isPrintLog()) {
            e.printStackTrace();
        }
    }

    public static int getImageWidth(Context context, int resId) {
        ImageView iv_temp = new ImageView(context);
        iv_temp.setImageResource(resId);
        iv_temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return iv_temp.getDrawable().getIntrinsicWidth();
    }

    public static int getImageHeight(Context context, int resId) {
        ImageView iv_temp = new ImageView(context);
        iv_temp.setImageResource(resId);
        iv_temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return iv_temp.getDrawable().getIntrinsicHeight();
    }
}
