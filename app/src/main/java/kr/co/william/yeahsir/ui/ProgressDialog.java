package kr.co.william.yeahsir.ui;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by sheo on 2018-03-13.
 */

public class ProgressDialog extends android.app.ProgressDialog {

    private static ProgressDialog pocProgressDialog;
    private static ProgressBar progressBar;

    public ProgressDialog(Context context) {
        super(context);
    }

    public static void showProgress(Activity activity) {

        if (!activity.isFinishing()) {
            try {
                if (pocProgressDialog != null) {
                    pocProgressDialog.dismiss();
                }
                pocProgressDialog = new ProgressDialog(activity);
                pocProgressDialog.setCancelable(false);
                pocProgressDialog.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopProgress() {
        try {
            if (pocProgressDialog != null) {
                pocProgressDialog.dismiss();
                pocProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
