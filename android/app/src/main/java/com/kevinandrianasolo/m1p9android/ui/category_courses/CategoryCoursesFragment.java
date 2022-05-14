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
        Category currentCategory = (Category) bundle.get("category");

        /**
         * Binding parameters to the view :
         */
        TextView categoryTitleTextView = view.findViewById(R.id.course_category_title);
        categoryTitleTextView.setText(currentCategory.getTitle());

        /**
         * Retrieve course list of the current category, and bind it to coursesListView
         */
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(new Course("Trouver la parfaite boite avec l'Escouade des monstres", "Avec ludikids, les enfants seront exposés aux nombres, aux formes, aux calculs, aux concepts d’additions et de soustractions.", "https://ex1.o7planning.com/_testdatas_/mov_bbb.mp4", "https://i.picsum.photos/id/961/200/200.jpg?hmac=gHwrXvhjUL97oGKmAYQn508wdQ_V5sE9P64erzR-Ork"));
        courseList.add(new Course("Jusqu’à 10 avec les Numberblocks", "Pas de description", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "https://i.picsum.photos/id/593/200/200.jpg?hmac=E26lTUTkzs_AeuWXrkT-kFTudfYDTVCjgKVE_HDzRmk"));
        courseList.add(new Course("Le soleil, une super-étoile", "Avec ludikids, les enfants acquerront de la connaissance sur les sciences, la nature, la technologie, le monde environnant et sur la façon dont les choses fonctionnent.", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4", "https://i.picsum.photos/id/352/200/200.jpg?hmac=HPgFQ0Sto_7261sbYIaRW0-z2Jq0-C92RSt0vkdC6Uc"));

        ListView coursesListView = view.findViewById(R.id.courses_list_view);
        coursesListView.setAdapter(new CourseAdapter(view.getContext(), courseList));

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CategoryCoursesViewModel.class);
        // TODO: Use the ViewModel
    }

}