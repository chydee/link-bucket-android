package com.hoodlums.linkbucket.ui.features.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hoodlums.linkbucket.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private var isSignInRequest = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        configureTabLayout()
    }

    private fun configureTabLayout() {
        binding.authOptionsTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        isSignInRequest = true
                    }
                    1 -> {
                        isSignInRequest = false
                    }
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        isSignInRequest = true
                    }
                    1 -> {
                        isSignInRequest = false
                    }
                }
            }

        })
    }
}