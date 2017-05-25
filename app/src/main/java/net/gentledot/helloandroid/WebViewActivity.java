package net.gentledot.helloandroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    EditText urlInput;
    Button turnOnBtn;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webViewArea);
        urlInput = (EditText) findViewById(R.id.urlInput);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // 자바스크립트 사용 설정(기본)

        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
        webView.loadUrl("file:///android_asset/sample.html");

        turnOnBtn = (Button) findViewById(R.id.turnOnWebBtn);
        turnOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlInput.getText().toString());
            }
        });

    }

    final class JavaScriptMethods{

        // 애플리케이션에서 정의한 메소드로 웹페이지에서 호출할 대상
        @android.webkit.JavascriptInterface
        public void clickOnFace(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    turnOnBtn.setText("클릭 후 열기");
                    webView.loadUrl("javascript:changeFace()");
                }
            });
        }
    }

    final class WebBrowserClient extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();

            return true;
        }
    }



}
