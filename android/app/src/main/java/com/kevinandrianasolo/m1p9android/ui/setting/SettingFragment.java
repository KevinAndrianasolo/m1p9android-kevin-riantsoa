package com.kevinandrianasolo.m1p9android.ui.setting;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.utils.NotificationUtils;
import com.kevinandrianasolo.m1p9android.utils.SharedPreferencesUtils;

public class SettingFragment extends Fragment {

    private SettingViewModel mViewModel;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        Button nofifyBtn = view.findViewById(R.id.notifyBtn);

        NotificationUtils notificationUtils = NotificationUtils.getInstance();
        notificationUtils.initNotificationChannel(view.getContext());
        nofifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textTitle = "Notification Title";
                String textContent = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.";
                notificationUtils.showBasicNotification(view.getContext(), textTitle, textContent);
            }
        });

        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
        SharedPreferences sharedPref = sharedPreferencesUtils.getSharedPreferences();
        Boolean isNotificationActivated = sharedPref.getBoolean(getString(R.string.preferences_notifications), true);
        Switch notificationSwicth = view.findViewById(R.id.notification_switch_preference);
        notificationSwicth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /**
                 * Change preferences when toggle notificationSwicth
                 */
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.preferences_notifications), b);
                editor.commit();
            }
        });
        notificationSwicth.setChecked(isNotificationActivated);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        // TODO: Use the ViewModel
    }

}