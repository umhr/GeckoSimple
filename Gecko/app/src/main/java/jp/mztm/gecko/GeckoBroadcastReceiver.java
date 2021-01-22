package jp.mztm.gecko;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public final class GeckoBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeckoBroadcastReceiver";
    public MainActivity mainActivity;
    public WebViewManager webViewManager;

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_VIEW)) {
            // adb -s 192.168.1.12:5555 shell am broadcast -a android.intent.action.VIEW --es "url" "http://mztm.jp"
            Bundle extras = intent.getExtras();
            if(extras != null){
                String url = extras.getString("url");
                url = url.replace("%26", "&");
                Log.d(TAG, "url:"+url);
                if(url != null){
                    Log.d(TAG, url);
                    webViewManager.setLoadUrl(url);
                    //TextView tv = mainActivity.findViewById(R.id.tv);
                    //tv.setText(url);
                }
            }
        }
    }
}
