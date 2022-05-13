package com.kevinandrianasolo.m1p9android.ui.category_courses;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.adapters.CourseAdapter;
import com.kevinandrianasolo.m1p9android.models.Category;
import com.kevinandrianasolo.m1p9android.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CategoryCoursesFragment extends Fragment {

    private CategoryCoursesViewModel mViewModel;

    public static CategoryCoursesFragment newInstance() {
        return new CategoryCoursesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        Category currentCategory = (Category) bundle.get("category");

        View view = inflater.inflate(R.layout.category_courses_fragment, container, false);

        TextView categoryTitleTextView = view.findViewById(R.id.course_category_title);
        categoryTitleTextView.setText(currentCategory.getTitle());

        // Getting courseList of currentCategory
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("Trouver la parfaite boite avec l'Escouade des monstres", "Avec ludikids, les enfants seront exposés aux nombres, aux formes, aux calculs, aux concepts d’additions et de soustractions.", "cours_maths_1"));
        courseList.add(new Course("Jusqu’à 10 avec les Numberblocks", "Pas de description", "cours_maths_2"));
        courseList.add(new Course("Le soleil, une super-étoile", "Avec ludikids, les enfants acquerront de la connaissance sur les sciences, la nature, la technologie, le monde environnant et sur la façon dont les choses fonctionnent.", "cours_maths_3"));

        ListView coursesListView = view.findViewById(R.id.courses_list_view);
        coursesListView.setAdapter(new CourseAdapter(view.getContext(), courseList));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CategoryCoursesViewModel.class);
        // TODO: Use the ViewModel
    }

}