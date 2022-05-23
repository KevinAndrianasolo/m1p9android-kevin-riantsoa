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
import android.widget.EditText;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.services.UserService;
import com.kevinandrianasolo.m1p9android.utils.NotificationUtils;
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

        EditText usernameEditText = view.findViewById(R.id.login_username);
        EditText passwordEditText = view.findViewById(R.id.login_password);

        usernameEditText.setText("kevin.andrianasolo.lala@gmail.com");
        passwordEditText.setText("123456");

        NotificationUtils notificationUtils = NotificationUtils.getInstance();
        notificationUtils.initNotificationChannel(view.getContext());
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                UserService userService = new UserService(context);
                userService.login(email, password, new UserService.login() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String token) {
                        /**
                         * Saving tokenUser in the global SharedPreference
                         */
                        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
                        SharedPreferences sharedPref = sharedPreferencesUtils.getSharedPreferences();
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString(getString(R.string.preferences_tokenUser), token);
                        editor.commit();

                        /**
                         * Redirecting to home after Login succeed
                         */
                        NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                        navController.navigate(R.id.nav_home);

                        notificationUtils.showBasicNotification(view.getContext(), "Alerte de connexion", "Le compte "+email+ " est connecté.");
                    }
                });


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