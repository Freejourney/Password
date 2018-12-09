package com.example.admin.password.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.password.R;
import com.example.admin.password.utils.FingerPrintUtils;

/*
 * 文件名：FingerActivity
 * 作者：created by admin on 2018 十一月
 * 描述：
 */public class FingerActivity extends FragmentActivity {

    private ImageView mShakeImage;
    private TextView mTryText;
    private int mCount=5;
    private FingerPrintUtils mFingerUtils;
    private TranslateAnimation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);

        mTryText= (TextView) findViewById(R.id.try_text);
        mTryText.setText("请轻触感应器验证指纹");
        mShakeImage = (ImageView) findViewById(R.id.image_finger);
        mAnimation=new TranslateAnimation(0,5,0,0);
        mAnimation.setDuration(800);
        mAnimation.setInterpolator(new CycleInterpolator(8));
        mFingerUtils=new FingerPrintUtils(this);
        mFingerUtils.setFingerPrintListener(new FingerCallBack());
    }


    private class FingerCallBack extends FingerprintManagerCompat.AuthenticationCallback{
        //多次识别失败,并且，不能短时间内调用指纹验证
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            if (mCount>1){
                mCount--;
                mTryText.setText("指纹不匹配，还可以尝试"+mCount+"次");
            }else {
                mTryText.setText("1分钟后可重试!");
            }
            mHandler.sendMessageDelayed(new Message(), 1000 * 60);
        }

        //出错可恢复
        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
        }

        //识别成功
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            mFingerUtils.stopsFingerPrintListener();
            startActivity(new Intent(FingerActivity.this, MainActivity.class));
            finish();
            Toast.makeText(FingerActivity.this, "识别成功+++", Toast.LENGTH_SHORT).show();

        }

        //识别失败
        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            if (mCount>1){
                mCount--;
                mTryText.setText("指纹不匹配，还可以尝试"+mCount+"次");
            }
            mShakeImage.startAnimation(mAnimation);
        }
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mFingerUtils!=null){
                mFingerUtils.reSetFingerPrintListener(new FingerCallBack());
            }
            mCount=5;
            if (mTryText!=null) {
                mTryText.setText("请轻触感应器验证指纹");
            }

        }
    };
}
