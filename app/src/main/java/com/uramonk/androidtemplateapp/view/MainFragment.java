package com.uramonk.androidtemplateapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.RxFragment;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.databinding.FragmentMainBinding;
import com.uramonk.androidtemplateapp.viewmodel.MainFragmentViewModel;

/**
 * Created by uramonk on 2016/06/23.
 */
public class MainFragment extends RxFragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        MainFragmentViewModel mainFragmentViewModel = new MainFragmentViewModel(this);
        binding.setMainFragmentViewModel(mainFragmentViewModel);

        return binding.getRoot();
    }
}
