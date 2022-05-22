package com.kevinandrianasolo.m1p9android.ui.category_courses;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.adapters.CourseAdapter;
import com.kevinandrianasolo.m1p9android.models.CourseTheme;
import com.kevinandrianasolo.m1p9android.models.Course;
import com.kevinandrianasolo.m1p9android.services.CourseService;
import com.kevinandrianasolo.m1p9android.services.CourseThemeService;

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
        /**
         * Inflate the layout of CategoryCourses Fragment and initialize the corresponding view
         */
        View view = inflater.inflate(R.layout.category_courses_fragment, container, false);
        this.init(view);
        return view;
    }

    public void init(View view){
        /**
         * Getting fragment parameters : "Category"
         */
        Bundle bundle = this.getArguments();

        CourseTheme currentCourseTheme = (CourseTheme) bundle.get("category");

        /**
         * Binding parameters to the view :
         */
        TextView categoryTitleTextView = view.findViewById(R.id.course_category_title);
        categoryTitleTextView.setText(currentCourseTheme.getName());

        /**
         * Retrieve course list of the current category, and bind it to coursesListView
         */
        CourseService courseServ = new CourseService(view.getContext());
        ListView coursesListView = view.findViewById(R.id.courses_list_view);
        courseServ.getAllCourseByTheme(currentCourseTheme.getId(), new CourseService.allCourseByTheme() {
            @Override
            public void onError(String message) {
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Course> courseList) {
                coursesListView.setAdapter(new CourseAdapter(view.getContext(), courseList));
            }
        });


        /**
         * Initialize course search view :
         */
        SearchView searchView = view.findViewById(R.id.course_search_view);
        TextView searchCourseMessage = view.findViewById(R.id.search_course_message);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                /**
                 * Retrieve filtered data from API and update ListView Items
                 */
                courseServ.getAllCourseByTitle(currentCourseTheme.getId(), s, new CourseService.allCourseByTitle() {
                    @Override
                    public void onError(String message) {
                    }
                    @Override
                    public void onResponse(List<Course> courseListFiltered) {
                        coursesListView.setAdapter(new CourseAdapter(view.getContext(), courseListFiltered));
                        if(courseListFiltered.size() == 0) searchCourseMessage.setVisibility(View.VISIBLE);//.setVisibility(View.VISIBLE);
                        else searchCourseMessage.setVisibility(View.INVISIBLE);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryCoursesViewModel.class);
        // TODO: Use the ViewModel
    }

}