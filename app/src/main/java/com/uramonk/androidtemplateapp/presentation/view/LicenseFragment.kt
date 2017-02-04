package com.uramonk.androidtemplateapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trello.rxlifecycle.components.RxFragment
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.databinding.FragmentLicenseBinding

import com.uramonk.androidtemplateapp.presentation.viewmodel.LicenseFragmentViewModel

class LicenseFragment : RxFragment() {
    private lateinit var binding: FragmentLicenseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.fragment_license, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentLicenseBinding.bind(view)
        val licenseFragmentViewModel = LicenseFragmentViewModel(this)
        binding.licenseFragmentViewModel = licenseFragmentViewModel
    }

    companion object {
        fun newInstance(): LicenseFragment {
            return LicenseFragment()
        }
    }

}
