package com.kevinandrianasolo.m1p9android.ui.category;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.adapters.CategoryAdapter;
import com.kevinandrianasolo.m1p9android.models.CourseTheme;
import com.kevinandrianasolo.m1p9android.services.CourseThemeService;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        /**
         * Inflate the layout of CategoryCourses Fragment and initialize the corresponding view
         */
        View view =  inflater.inflate(R.layout.category_fragment, container, false);
        this.init(view);
        return view;
    }

    public void init(View view){
        /**
         * Retrieve categoryList from server and bind it to categoryListView
         */
        CourseThemeService courseServ = new CourseThemeService(view.getContext());
         courseServ.getAllCourseTheme(1, new CourseThemeService.allCourse() {
            @Override
            public void onError(String message) {

            }
            @Override
            public void onResponse(List<CourseTheme> courseThemeList) {
                ListView categoryListView = view.findViewById(R.id.category_fragment_list_view);
                categoryListView.setAdapter(new CategoryAdapter(view.getContext(), courseThemeList));
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel


    }

}