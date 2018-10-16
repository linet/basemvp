package com.baseljc.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


//import com.linet.mvp.R2;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/6/12.
 */
public class BaseWebviewActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R2.id._back_button)
    Button BackButton;
//    @BindView(R2.id.include_title_button)
    TextView includeTitleButton;
//    @BindView(R2.id.include_title_imageview)
    ImageView includeTitleImageview;
//    @BindView(R2.id.progress)
    ContentLoadingProgressBar progress;
//    @BindView(R2.id.webview_layout)
    LinearLayout webviewLayout;
    public static String TITLE = "title";
    public static String URL = "url";


    public static void startActivity(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, BaseWebviewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }


    protected WebView web_botice;

    protected String title;
    protected String url;
    protected String webContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.notice_news_data_activity);
        ButterKnife.bind(this);

        //使用new方式这是为了解决当(settings.setJavaScriptEnabled(true))之后会出现TextToSpeech的内存泄漏
        web_botice = new WebView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        web_botice.setLayoutParams(layoutParams);

        webviewLayout.addView(web_botice);

        includeTitleButton.setOnClickListener(this);
        includeTitleImageview.setOnClickListener(this);
        BackButton.setOnClickListener(this);


        includeTitleImageview.setVisibility(View.GONE);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        if (url == null) url = "";
//        LogUtils.i(TAG, "-------------------->url0=" + url);
        if (!TextUtils.isEmpty(url) && !url.startsWith("http")) {
            webContent = url;
            url = "";
        }


        if (title == null)
            title = "";

        setTitle(title);

        WebSettings settings = web_botice.getSettings();
        settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setAllowContentAccess(true);
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        settings.setSupportZoom(true);//是否可以缩放，默认true
//        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
//        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage
        // displayWebview.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
        web_botice.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title1) {

                if (TextUtils.isEmpty(title)) {
                    if (TextUtils.isEmpty(title)) {
                        title = title1;
                        includeTitleButton.setText(title);
                    }
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progress.setProgress(newProgress);
                if (newProgress >= 100) {
                    new Handler().postDelayed(() -> progress.setVisibility(View.GONE), 500);
                } else if (progress.getVisibility() != View.VISIBLE) {
                    progress.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        web_botice.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                web_botice.loadUrl(url);//这样调用容易死循环  如果不需要对地址做特殊重定向处理 最好别重写此方法
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                BaseWebviewActivity.this.onPageFinished();
            }
        });

        if (!TextUtils.isEmpty(url)) {
            setWebViewUrl(url);
        } else if (!TextUtils.isEmpty(webContent)) {
            setWebViewContent(webContent);
        }

        setRightButtonVisiable(false);
    }

    protected void onPageFinished() {

    }

    protected void setTitle(String title) {
        includeTitleButton.setText(title);
    }

    protected void setWebViewUrl(String urlStr) {
        web_botice.loadUrl(urlStr);
    }

    protected void setWebViewContent(String content) {
        web_botice.loadData(content, "text/html", "utf-8");
    }

    protected void setRightButtonVisiable(boolean visiable) {
        includeTitleImageview.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.include_title_imageview) {
//            onClickRightButton(v);
//        } else if (id == R.id._back_button) {
//            onBack(v);
//        }
    }

    @Override
    public void onBackPressed() {
        onBack(null);
//        super.onBackPressed();
    }

    protected void onClickRightButton(View v) {

    }

    protected void onBack(View v) {
        if (web_botice.canGoBack()) {
            web_botice.goBack();
        } else {
            finish();
        }
    }


}
