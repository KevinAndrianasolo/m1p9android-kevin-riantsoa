package com.kevinandrianasolo.m1p9android.ui.category;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kevinandrianasolo.m1p9android.R;
import com.kevinandrianasolo.m1p9android.adapters.CategoryAdapter;
import com.kevinandrianasolo.m1p9android.models.Category;

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
        View view =  inflater.inflate(R.layout.category_fragment, container, false);

        List<Category> categoryList = new ArrayList<Category>();

        categoryList.add(new Category("Anglais et Alphabétisation", "Ludikids introduit l’alphabétisation française et des compétences en anglais telles que la phonétique, les lettres et les mots.", "category_abc"));
        categoryList.add(new Category("Les Maths et Arithmétique", "Avec ludikids, les enfants seront exposés aux nombres, aux formes, aux calculs, aux concepts d’additions et de soustractions.", "category_maths"));
        categoryList.add(new Category("Sciences et Monde Autour De Nous", "Avec ludikids, les enfants acquerront de la connaissance sur les sciences, la nature, la technologie, le monde environnant et sur la façon dont les choses fonctionnent.", "category_sciences"));
        categoryList.add(new Category("L’apprentissage Social et Émotionnel", "Ludikids introduit des programmes pour encourager l’apprentissage social et émotionnel essentiel au développement de l’enfant.", "category_social"));

        ListView categoryListView = view.findViewById(R.id.category_fragment_list_view);
        categoryListView.setAdapter(new CategoryAdapter(view.getContext(), categoryList));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel.class);
        // TODO: Use the ViewModel


    }

}