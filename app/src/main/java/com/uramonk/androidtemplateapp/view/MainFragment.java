package com.uramonk.androidtemplateapp.view;

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
    private FragmentMainBinding binding;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentMainBinding.bind(getView());
        MainFragmentViewModel mainFragmentViewModel = new MainFragmentViewModel(this);
        binding.setMainFragmentViewModel(mainFragmentViewModel);
    }
}
