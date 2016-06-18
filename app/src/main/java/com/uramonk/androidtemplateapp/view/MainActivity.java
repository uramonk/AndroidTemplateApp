package com.uramonk.androidtemplateapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.databinding.ActivityMainBinding;
import com.uramonk.androidtemplateapp.viewmodel.MainViewModel;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel();
        binding.setMainViewModel(mainViewModel);
    }
}
