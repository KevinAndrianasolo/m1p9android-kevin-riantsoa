package com.kevinandrianasolo.m1p9android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.models.Category;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categoryList;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Category getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.adapter_category, null);
        Category currentCategory = this.getItem(i);

        ImageView imageCategory = view.findViewById(R.id.category_image);
        int resId = context.getResources().getIdentifier(currentCategory.getImg(), "drawable", context.getPackageName());
        imageCategory.setImageResource(resId);

        TextView titleTextView = view.findViewById(R.id.category_title);
        TextView descriptionTextView = view.findViewById(R.id.category_description);

        titleTextView.setText(currentCategory.getTitle());
        descriptionTextView.setText(currentCategory.getDescription());
        return view;
    }
}
