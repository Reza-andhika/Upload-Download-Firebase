package com.example.user.dpkb_jateng;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 08/06/2018.
 */

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    @Override

    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
