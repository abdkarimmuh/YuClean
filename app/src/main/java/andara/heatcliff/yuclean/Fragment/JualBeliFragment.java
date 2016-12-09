package andara.heatcliff.yuclean.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import andara.heatcliff.yuclean.R;

public class JualBeliFragment extends Fragment {

    public WebView webViewJualBeli;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_jual_beli, container, false);

        webViewJualBeli = (WebView) rootView.findViewById(R.id.webViewJualBeli);
        webViewJualBeli.setWebViewClient(new MyWebViewClient());

        String url = "http://yuclean.andara-tech.com/catalogs";
        webViewJualBeli.getSettings().setJavaScriptEnabled(true);
        webViewJualBeli.loadUrl(url);

        return rootView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
