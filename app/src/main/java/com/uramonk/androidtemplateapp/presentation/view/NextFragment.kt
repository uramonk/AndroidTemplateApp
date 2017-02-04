package com.uramonk.androidtemplateapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trello.rxlifecycle.components.RxFragment
import com.uramonk.androidtemplateapp.R
import com.uramonk.androidtemplateapp.databinding.FragmentNextBinding
import com.uramonk.androidtemplateapp.presentation.viewmodel.NextFragmentViewModel

/**
 * Created by uramonk on 2016/07/04.
 */
class NextFragment : RxFragment() {
    private lateinit var binding: FragmentNextBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentNextBinding.bind(view)
        val nextFragmentViewModel = NextFragmentViewModel(this)
        binding.nextFragmentViewModel = nextFragmentViewModel
    }

    companion object {
        fun newInstance(): NextFragment {
            return NextFragment()
        }
    }
}
