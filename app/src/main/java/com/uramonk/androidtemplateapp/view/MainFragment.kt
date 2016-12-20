package com.uramonk.androidtemplateapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jakewharton.rxbinding.view.RxView
import com.trello.rxlifecycle.components.RxFragment
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.databinding.FragmentMainBinding
import com.uramonk.androidtemplateapp.viewmodel.MainFragmentViewModel

import rx.Observable

/**
 * Created by uramonk on 2016/06/23.
 */
class MainFragment : RxFragment() {
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val mainFragmentViewModel = MainFragmentViewModel(this)
        binding!!.mainFragmentViewModel = mainFragmentViewModel
    }

    fun onButtonClicked(): Observable<Void> {
        return RxView.clicks(binding!!.mainFragmentButton)
    }

    fun onNextButtonClicked(): Observable<Void> {
        return RxView.clicks(binding!!.mainFragmentNextButton)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
