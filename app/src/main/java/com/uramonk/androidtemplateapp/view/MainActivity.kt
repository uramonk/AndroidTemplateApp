package com.uramonk.androidtemplateapp.view

import android.databinding.DataBindingUtil
import android.os.Bundle

import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.databinding.ActivityMainBinding
import com.uramonk.androidtemplateapp.viewmodel.MainViewModel

class MainActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val mainViewModel = MainViewModel(this)
        binding.mainViewModel = mainViewModel

        // Call after ViewModel was created
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        val backStackNum = fragmentManager.backStackEntryCount
        if (backStackNum > 1) {
            fragmentManager.popBackStack()
        } else {
            if (!moveTaskToBack(false)) finish()
        }
    }
}
