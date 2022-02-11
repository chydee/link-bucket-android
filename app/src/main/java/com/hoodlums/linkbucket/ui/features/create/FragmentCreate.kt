package com.hoodlums.linkbucket.ui.features.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hoodlums.linkbucket.R

class FragmentCreate : Fragment() {

    companion object {
        fun newInstance() = FragmentCreate()
    }

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}