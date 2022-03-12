package com.hoodlums.linkbucket.ui.features.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hoodlums.linkbucket.R
import com.hoodlums.linkbucket.databinding.FragmentMyBucketBinding
import com.hoodlums.linkbucket.ui.utils.autoCleared

class MyBucketFragment : Fragment() {

    private var binding: FragmentMyBucketBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_bucket, container, false).run {
            binding = FragmentMyBucketBinding.bind(this)
            this
        }
    }


}