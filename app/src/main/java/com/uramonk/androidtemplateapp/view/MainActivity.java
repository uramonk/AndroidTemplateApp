package com.uramonk.androidtemplateapp.view;

import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.uramonk.androidtemplateapp.R;
import com.uramonk.androidtemplateapp.databinding.ActivityMainBinding;
import com.uramonk.androidtemplateapp.viewmodel.MainViewModel;

public class MainActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel(this);
        binding.setMainViewModel(mainViewModel);

        // Call after ViewModel was created
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        int backStackNum = getFragmentManager().getBackStackEntryCount();
        if (backStackNum > 1) {
            getFragmentManager().popBackStack();
        } else {
            if (!moveTaskToBack(false)) finish();
        }
    }
}
