package com.kevinandrianasolo.m1p9android.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inspector.PropertyReader;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.utils.PropertiesUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private ProgressDialog progressDialog;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        WebView homeWebView = view.findViewById(R.id.home_webview);
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Accès au serveur, veuillez patienter...");
        // If no progress dialog, make one and set message
        progressDialog.show();
        // Hide the webview while loading
        homeWebView.setEnabled(false);

        homeWebView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                // Page is done loading;
                // hide the progress dialog and show the webview

                if (progressDialog!=null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                    homeWebView.setEnabled(true);
                }
            }
            public void onReceivedError(WebView view, int errorCod,String description, String failingUrl) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                Toast.makeText(view.getContext(), "Vous n'aviez pas accès à internet ou " + description , Toast.LENGTH_LONG).show();
            }
        });
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance(view.getContext());
        Properties properties = propertiesUtils.getProperties();
        if(properties!=null) {
            String serverUrl = properties.getProperty("server.url");
            String url = serverUrl + "/public/pages/home/index.html";
            homeWebView.loadUrl(url);
        }
        else Toast.makeText(view.getContext(), "Le fichier de configuration application.properties n'a pas été trouvé.", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}