package com.uramonk.androidtemplateapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.RxFragment;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.databinding.FragmentNextBinding;
import com.uramonk.androidtemplateapp.viewmodel.NextFragmentViewModel;

/**
 * Created by uramonk on 2016/07/04.
 */
public class NextFragment extends RxFragment {
    private FragmentNextBinding binding;

    public static NextFragment newInstance() {
        return new NextFragment();
    }

    public NextFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentNextBinding.bind(getView());
        NextFragmentViewModel nextFragmentViewModel = new NextFragmentViewModel(this);
        binding.setNextFragmentViewModel(nextFragmentViewModel);
    }
}
