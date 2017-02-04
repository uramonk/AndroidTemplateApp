package com.uramonk.androidtemplateapp.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.RxFragment;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.databinding.FragmentLicenseBinding;

import com.uramonk.androidtemplateapp.presentation.viewmodel.LicenseFragmentViewModel;

public class LicenseFragment extends RxFragment {
    private FragmentLicenseBinding binding;

    public LicenseFragment() {

    }

    public static LicenseFragment newInstance() {
        return new LicenseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_license, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentLicenseBinding.bind(getView());
        LicenseFragmentViewModel licenseFragmentViewModel = new LicenseFragmentViewModel(this);
        binding.setLicenseFragmentViewModel(licenseFragmentViewModel);
    }

}
