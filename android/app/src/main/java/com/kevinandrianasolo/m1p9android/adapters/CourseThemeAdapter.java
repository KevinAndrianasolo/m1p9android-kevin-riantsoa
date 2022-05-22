package com.kevinandrianasolo.m1p9android.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Category;
import com.kevinandrianasolo.m1p9android.models.CourseTheme;
import com.kevinandrianasolo.m1p9android.utils.MediaUtils;

import java.util.List;

public class CourseThemeAdapter extends BaseAdapter {

    private Context context;
    private List<CourseTheme> categoryList;
    private LayoutInflater inflater;

    public CourseThemeAdapter(Context context, List<CourseTheme> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public CourseTheme getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.category_adapter, null);
        CourseTheme currentCategory = this.getItem(i);

        /**
         * Bind the image to imageView
         */
        ImageView imageCategory = view.findViewById(R.id.category_image);
        MediaUtils.loadImage(imageCategory, currentCategory.getImage_path());

        /**
         * Set Category.title and Category.description
         */
        TextView titleTextView = view.findViewById(R.id.category_title);
        TextView descriptionTextView = view.findViewById(R.id.category_description);
        titleTextView.setText(currentCategory.getName());
        descriptionTextView.setText(currentCategory.getDescription());

        /**
         * Set listener to the category, when click navigate to courses list of the current Category
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Pass category as a parameter for CategoryCoursesFragment
                 */
                Bundle bundle = new Bundle();
                bundle.putSerializable("category", currentCategory);

                /**
                 * Navigate to CategoryCoursesFragment with parameters
                 */
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_category_courses, bundle); // Pass the categoryId to fragment
            }
        });

        return view;
    }
}
