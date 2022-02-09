package com.hoodlums.linkbucket.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hoodlums.linkbucket.R

class FragmentAuth : Fragment() {

    companion object {
        fun newInstance() = FragmentAuth()
    }

    private lateinit var viewModel: FragmentAuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentAuthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}