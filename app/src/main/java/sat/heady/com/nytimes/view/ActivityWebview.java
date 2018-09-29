package sat.heady.com.nytimes.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import sat.heady.com.nytimes.R;

public class ActivityWebview extends AppCompatActivity {

    private static CustomWebView mWebView;
    private RelativeLayout noDataRL;
    private ProgressBar mProgressBar;
    private Button retry;
    private boolean ISBTNCLICKED=false;
    String mainurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mainurl=getIntent().getStringExtra("url");
        initWebView();
    }

    private void initWebView() {
        mWebView = (CustomWebView) findViewById(R.id.webviews);
        noDataRL = (RelativeLayout)findViewById(R.id.noDataRL);
        mProgressBar= (ProgressBar) findViewById(R.id.mProgressBar);
        retry=(Button)findViewById(R.id.retry);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        CookieManager cookieManager=CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        mWebViewClicked();
        if (isNetworkAvailable()){
            try {
                //clearSession();
            }catch (Exception d){}
            mWebView.loadUrl(mainurl);
            mWebView.setVisibility(View.VISIBLE);
            noDataRL.setVisibility(View.GONE);
        }else{
            mWebView.setVisibility(View.GONE);
            noDataRL.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
//            singelButtonAlertDialog(this,"Ok","No internet Available.","Please try aagain after sometime.",true);
//            Toast.makeText(getApplicationContext(),"No Internet Available.",Toast.LENGTH_SHORT).show();
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()){
//                        mWebView.reload();
                    ISBTNCLICKED=true;
                    mWebView.loadUrl( "javascript:window.location.reload( true )" );
                }else{
                    Toast.makeText(getApplicationContext(),"No Internet Available.",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ActivityWebview.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void mWebViewClicked(){
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
//                url=url.replace("-","");
//                Log.d("url ",url);
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
//                Toast.makeText(MainActivity.this, "url "+url, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onPageFinished(WebView view, String url) {
//                url=url.replace("-","_");
                super.onPageFinished(view, url);
                if (ISBTNCLICKED){
                    if (isNetworkAvailable()){
                        mWebView.setVisibility(View.VISIBLE);
                        noDataRL.setVisibility(View.GONE);
                    }else{
                        mWebView.setVisibility(View.GONE);
                        noDataRL.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
//                    Toast.makeText(getApplicationContext(),"No Internet Available.",Toast.LENGTH_SHORT).show();
                    }

                    ISBTNCLICKED=false;
                }
                mProgressBar.setVisibility(View.GONE);

            }

           /* @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.v("errorCode rrrrr ","onReceivedError "+error);
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.GONE);
                noDataRL.setVisibility(View.VISIBLE);
                if (isNetworkAvailable()) {
                    mWebView.reload();
                }

            }*/

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                Log.v("errorCode rrrrr ","onReceivedHttpError "+errorResponse);

                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.GONE);
                noDataRL.setVisibility(View.VISIBLE);
                if (isNetworkAvailable()){
                    mWebView.reload();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.v("errorCode rrrrr ","onReceivedError "+errorCode +" : "+ failingUrl +" : "+  description);
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.GONE);
                noDataRL.setVisibility(View.VISIBLE);
                if (isNetworkAvailable()){
                    mWebView.reload();
                }
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.GONE);
                noDataRL.setVisibility(View.VISIBLE);
                if (isNetworkAvailable()){
                    mWebView.reload();
                }
                //Toast.makeText(TableContentsWithDisplay.this, "error "+error, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void clearSession(){
        CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(ActivityWebview.this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        mWebView.clearCache(true);
        mWebView.clearHistory();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.getUrl().equalsIgnoreCase(mainurl)){
            finish();
        }else{
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
