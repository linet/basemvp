package com.baseljc.mvpdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import butterknife.BindView
import butterknife.OnClick
import com.baseljc.mvp.base.BaseActivity
import com.baseljc.mvp.base.BaseApplication
import com.baseljc.mvp.base.BaseViewState
import com.baseljc.mvpdemo.contact.MainView
import com.baseljc.mvpdemo.dagger.components.DaggerMainComponent
import com.baseljc.mvpdemo.dagger.components.MainComponent
import com.baseljc.mvpdemo.presenter.MainPresenter
import com.blikoon.qrcodescanner.QrCodeActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainComponent, MainView, MainPresenter, BaseViewState<MainView>>() {

    val REQUEST_CODE_QR_SCAN = 101
//    var mWebChromeClient: WebChromeClient


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun constructComponent(): MainComponent {
        return DaggerMainComponent.builder()
                .applicationComponent(BaseApplication.getApplicationComponent())
                .build()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        var settings = webview.settings
        settings.domStorageEnabled = true
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
//        settings.allowFileAccessFromFileURLs = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.defaultTextEncodingName = "utf-8"
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false
        settings.setSupportZoom(false)

//        mWebChromeClient = WebChromeClient {
//
//
//
//        }



        et_input.setOnEditorActionListener { textView, actionId, keyEvent ->

            var isGo = actionId == EditorInfo.IME_ACTION_SEND ||
                    (keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER || keyEvent.action == KeyEvent.ACTION_DOWN))
            var url = et_input.text.trim()

            if (isGo && !TextUtils.isEmpty(url)) {
                reloadWebView(url as String)
            }
            false
        }
    }


    fun reloadWebView(url: String) {

        if (!TextUtils.isEmpty(url)) {
            webview.loadUrl(url)
        }

    }


    @OnClick(R.id.et_input)
    fun onClick(view: View) {

        if (view.id == R.id.et_input) {

            //Start the qr scan activity
            var i = Intent(this, QrCodeActivity::class.java)

            startActivityForResult(i, REQUEST_CODE_QR_SCAN)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }
}
