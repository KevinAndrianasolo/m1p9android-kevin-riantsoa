package com.kevinandrianasolo.m1p9android.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.utils.SharedPreferencesUtils;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        Button loginBtn = view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Saving userId in the global SharedPreference
                 */
                String userId = "user1";
                SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
                SharedPreferences sharedPref = sharedPreferencesUtils.getSharedPreferences();
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString(getString(R.string.preferences_userId), userId);
                editor.commit();

                /**
                 * Redirecting to home after Login succeed
                 */
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_home);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

}