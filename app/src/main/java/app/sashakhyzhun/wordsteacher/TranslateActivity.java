package app.sashakhyzhun.wordsteacher;


import android.os.Bundle;
import android.webkit.WebView;

public class TranslateActivity extends MainActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        String url = "http://translate.google.com.ua/?hl=ru&tab=wT";
        WebView webview  = (WebView) this.findViewById(R.id.webview);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        finish();

    }



}
