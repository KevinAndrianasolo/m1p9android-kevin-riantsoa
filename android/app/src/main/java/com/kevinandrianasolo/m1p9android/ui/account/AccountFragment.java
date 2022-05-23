package com.kevinandrianasolo.m1p9android.ui.account;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.User;
import com.kevinandrianasolo.m1p9android.services.UserService;
import com.kevinandrianasolo.m1p9android.utils.SharedPreferencesUtils;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);


        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        SharedPreferences sharedPref = sharedPreferencesUtils.getSharedPreferences();
        String tokenUser = sharedPref.getString(getString(R.string.preferences_tokenUser) , null);

        TextView lastname = view.findViewById(R.id.account_lastname);
        TextView firstname = view.findViewById(R.id.account_firstname);
        if(tokenUser!=null){
            // Setting user informations :
            Context context = view.getContext();
            UserService userService = new UserService(context);
            userService.getUserDetails(tokenUser, new UserService.profile() {
                @Override
                public void onError(String message) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(User user) {
                    Toast.makeText(context, "USERNAME : "+user.getName(), Toast.LENGTH_SHORT).show();
                    lastname.setText(user.getName());
                    lastname.setText(user.getFirstname());
                }
            });
        }
        else{
            Toast.makeText(view.getContext(), "Aucun utilisateur n'est connecté", Toast.LENGTH_SHORT).show();
        }
        Button logoutBtn = view.findViewById(R.id.account_logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /**
                 * Deleting userId in the global SharedPreference
                 */

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(getString(R.string.preferences_tokenUser));
                editor.commit();

                /**
                 * Redirect to login after logout succeed
                 */
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_login);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        // TODO: Use the ViewModel
    }

}