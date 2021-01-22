package jp.mztm.gecko;
import android.view.KeyEvent;

import androidx.lifecycle.ViewModelProvider;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

import java.io.File;

public class WebViewManager {
    private static final String TAG = "WebViewManager";
    public GeckoView webView;
    public GeckoSession session;
    public URLManager urlManager;

    public WebViewManager(final GeckoView webView, GeckoRuntime runtime, File path){
        this.webView = webView;

        urlManager = new URLManager(path);

        //GeckoView view = findViewById(R.id.geckoview);
        this.session = new GeckoSession();
        //GeckoRuntime runtime = GeckoRuntime.create(this);

        this.session.open(runtime);
        webView.setSession(this.session);
        /*
        webView.setFocusableInTouchMode(false);
        webView.setFocusable(true);
        this.session.setFocused(true);
        */
        this.setLoadUrl(urlManager.home);
        //this.session.loadUri("http://bb.mztm.jp:5004/suimei/view2.html");
        //this.setLoadUrl("http://bb.mztm.jp:5004/suimei/view2.html");
        //http://bb.mztm.jp/longrun/
        //viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    public void setLoadUrl(String url){
        //Log.i(TAG, url);
        this.session.loadUri(url);
        urlManager.write(url);
    }

    public void setKeyCode(int keyCode){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            this.session.goBack();
        }
    }
}
