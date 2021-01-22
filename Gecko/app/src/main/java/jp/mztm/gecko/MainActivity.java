package jp.mztm.gecko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GeckoBroadcastReceiver geckoBroadcastReceiver;
    private WebViewManager webViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewManager = new WebViewManager(findViewById(R.id.geckoview), GeckoRuntime.create(this), getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));

        geckoBroadcastReceiver = new GeckoBroadcastReceiver();
        registerReceiver(geckoBroadcastReceiver, new IntentFilter(Intent.ACTION_VIEW));
        geckoBroadcastReceiver.mainActivity = this;
        geckoBroadcastReceiver.webViewManager = webViewManager;
        /*
        GeckoView view = findViewById(R.id.geckoview);
        GeckoSession session = new GeckoSession();
        GeckoRuntime runtime = GeckoRuntime.create(this);

        session.open(runtime);
        view.setSession(session);
        session.loadUri("http://bb.mztm.jp:5004/suimei/view2.html");
        //session.loadUri("http://bb.mztm.jp/longrun/");
        */
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //return super.onKeyDown(keyCode, event);
        Log.i(TAG,"keyCode = " + keyCode);
        if(keyCode == KeyEvent.KEYCODE_MENU){

        }else if(keyCode == KeyEvent.KEYCODE_BACK){
            webViewManager.setKeyCode(keyCode);
            return true;
        }
        return false;
    }
}