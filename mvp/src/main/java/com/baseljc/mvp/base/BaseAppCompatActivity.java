package com.baseljc.mvp.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;


import com.baseljc.mvp.base.action.BaseActionActivity;

import javax.annotation.Nullable;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * 为加载fragment抽出来的，此activity不需要获取数据显示因此没有MVP类
 * Created by 0200030 on 2017/9/9.
 * 继承 BaseActionActivity
 */
public abstract class BaseAppCompatActivity extends BaseActionActivity implements LoadMvpView {

    private boolean isCloseBackKey = false;
    private boolean isOpenDoubleClickToExit = false;
    private long exitTime = 0;

//    private LoadingDialog _processBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    protected abstract int getLayoutId();

    public void backClick(View v) {
        finish();
    }

    /**
     * 关闭返回键
     */
    public void closeBackKey() {
        this.isCloseBackKey = true;
    }

    /**
     * 打开返回键
     */
    public void openBackkey() {
        this.isCloseBackKey = false;
    }

    /**
     * 关闭二次点击退出功能
     */
    public void closeDoubleClickToExit() {
        this.isOpenDoubleClickToExit = false;
    }

    /**
     * 打开二次点击退出功能
     */
    public void openDoubleClickToExit() {
        this.isOpenDoubleClickToExit = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        try {
            if (isCloseBackKey) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
            }
            if (isOpenDoubleClickToExit) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if ((System.currentTimeMillis() - exitTime) > 2000) {
                        exitTime = System.currentTimeMillis();
                        return true;
                    } else {
//                        ActivityManager2.getInstence(getApplication()).exitApp();
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showLoadingBar() {
        Timber.v("Showing loading bar");
        showDialog();
    }

    @Override
    public void dismissLoadingBar() {
        Timber.v("dismiss Loading Bar.");
        closeDialog();
    }

    @Override
    public void showLoadingFailureError() {
        Timber.v("Showing loading failure layouts.");
    }

    /**
     * 开发测试用,非正式项目
     */
    public void showDialog() {
        String message = "加载中";
        Message message1 = new Message();
        message1.what = 1;
        message1.obj = TextUtils.isEmpty(message) ? "" : message;
        mProcessDialogHandler.sendMessage(message1);
    }

    public void closeDialog() {
        mProcessDialogHandler.sendEmptyMessage(0);
    }

    protected Handler mProcessDialogHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

//            if (msg.what == 1) {
//                try {
//                    if (_processBar == null) {
//                        _processBar = new LoadingDialog(BaseAppCompatActivity.this);
//                        _processBar.setCanceledOnTouchOutside(false);
//                        _processBar.setCancelable(true);
//                    }
//
//                    if (_processBar.isShowing()) {
//                        _processBar.dismiss();
//                    }
//
//                    String str = msg.obj.toString();
//                    if (TextUtils.isEmpty(str)) {
//                        str = getString(R.string.loading);
//                    }
////                    _processBar.setMessage(str);
//                    _processBar.show();
//                }  catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//                try {
//                    if (null != _processBar) {
//                        _processBar.cancel();
//                    }
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }
    };
}
