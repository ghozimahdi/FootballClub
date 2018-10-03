package com.module.footballclub.conn;

import android.util.Log;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by knalb on 02/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer Expert
 */
public class ApiObserver implements Observer<String> {


    /**
     * Rest Api Code Tutorial https://www.restapitutorial.com/httpstatuscodes.html
     */

    private String TAG = "ApiObserver";
    private String stringErrorBody = "HTTP Response Error Body()";
    private String responseError = "HTTP Response Message()";
    private OnCallBack onCallBack;
    private String responseBody = "";
    private int code = 0;

    public ApiObserver(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.e(TAG, "onSubscribe " + Thread.currentThread().getName());
    }

    @Override
    public void onNext(String s) {
        Log.e(TAG, "onNext: " + Thread.currentThread().getName());
        Log.e(TAG, "Response: " + s);
        responseBody = s;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.getMessage());
        if (e instanceof HttpException) {
            Response response = ((HttpException) e).response();

            ResponseError resError = new ResponseError();
            resError.setProtocol(response.raw().protocol().toString());
            resError.setCode(response.raw().code());
            resError.setMessage(response.raw().message());
            resError.setUrl(response.raw().request().url().toString());

            code = response.raw().code();

            // get Response
            Log.d(responseError, "" + resError.toString());
            Log.d(responseError, "" + response.toString());

            try {
                String responseErrorBody = response.errorBody().string();

                // get Response Error Body
                Log.d(stringErrorBody, responseErrorBody);

                onCallBack.error(responseErrorBody, resError);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: All Done! " + Thread.currentThread().getName());
        onCallBack.succses(responseBody);
    }
}
