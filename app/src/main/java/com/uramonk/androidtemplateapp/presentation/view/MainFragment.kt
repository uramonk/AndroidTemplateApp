package com.uramonk.androidtemplateapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView

import com.trello.rxlifecycle.components.RxFragment
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.databinding.FragmentMainBinding
import com.uramonk.androidtemplateapp.presentation.viewmodel.MainFragmentViewModel

import io.reactivex.Observable

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragment : RxFragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val mainFragmentViewModel = MainFragmentViewModel(this)
        binding.mainFragmentViewModel = mainFragmentViewModel
    }

    fun onButtonClicked(): Observable<Any> {
        return RxView.clicks(binding.mainFragmentButton)
    }

    fun onNextButtonClicked(): Observable<Any> {
        return RxView.clicks(binding.mainFragmentNextButton)
    }

    fun onLicenseButtonClicked(): Observable<Any> {
        return RxView.clicks(binding.mainFragmentLicenseButton)
    }

    fun onGetWeatherButtonClicked(): Observable<Any> {
        return RxView.clicks(binding.mainFragmentGetWeatherButton)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
