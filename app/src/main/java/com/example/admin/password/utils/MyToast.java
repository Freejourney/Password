package com.example.admin.password.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/*
 * 文件名：MyToast
 * 作者：created by admin on 2018 十二月
 * 描述：
 */public class MyToast {

     public static void show(Context context, CharSequence text, int duration) {
         Toast toast = Toast.makeText(context, text, duration);
         toast.show();
     }

     public static void showOnUIThreadx(final Context context, final CharSequence text, final int duration) {
         Handler handler = new Handler(Looper.getMainLooper());
         handler.post(new Runnable() {
             @Override
             public void run() {
                 Toast toast = Toast.makeText(context, text, duration);
                 toast.show();
             }
         });
     }
}
