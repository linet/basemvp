package com.baseljc.mvp.networking.exception;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.baseljc.mvp.networking.model.ResultModel;

/**
 * Created by gxj on 2017/12/5.
 * 接口异常同意处理工具类
 */

public class ResponseExceptionUtils {
    private String jsonString;
    private final Gson gson;

    public ResponseExceptionUtils(String jsonString, Gson gson) {
        this.jsonString = jsonString;
        this.gson = gson;
    }

    /**
     * 检测异常错误
     * 9996 -> IP 异常
     * 9997-> 登陆环境异常
     */
    public void parseErrorJsonString() {
        if (TextUtils.isEmpty(jsonString) || gson == null) {
            return;
        }
        try {
            ResultModel<String> resultModel = gson.fromJson(jsonString, ResultModel.class);
            if (resultModel.getCode() == 9996 || resultModel.getCode() == 9997) {
//                //加载报价
//                LoginProxy loginProxy = new LoginProxy();
//                ILoginService iQuotationUI = loginProxy.getServiceInterface();
//                if (iQuotationUI != null) {
//                    iQuotationUI.openLogin(BaseApplication.sApplication);
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测异常错误
     * 9996 -> IP 异常
     * 9997-> 登陆环境异常
     */
    public static void parseErrorJsonString(int code) {
        if (code == 9996 || code == 9997) {
            //加载报价
//            LoginProxy loginProxy = new LoginProxy();
//            ILoginService iQuotationUI = loginProxy.getServiceInterface();
//            if (iQuotationUI != null) {
//                iQuotationUI.openLogin(BaseApplication.sApplication);
//            }
        }
    }
}
