package com.hoodlums.linkbucket.ui.features.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hoodlums.linkbucket.R
import com.hoodlums.linkbucket.databinding.FragmentAccountBinding
import com.hoodlums.linkbucket.ui.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false).run {
            binding = FragmentAccountBinding.bind(this)
            this
        }
    }

}