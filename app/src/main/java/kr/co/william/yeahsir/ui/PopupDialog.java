package kr.co.william.yeahsir.ui;

import android.app.AlertDialog;
import android.content.Context;

import kr.co.william.yeahsir.ui.activity.MainActivity;

/**
 * Created by sheo on 2018-03-13.
 */

public class PopupDialog {

    public static void show(Context context, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("알림").setMessage(msg).setPositiveButton("확인", null);
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }
}
