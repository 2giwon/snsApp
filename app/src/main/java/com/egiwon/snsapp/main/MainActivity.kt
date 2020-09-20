package com.egiwon.snsapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.egiwon.snsapp.EventObserver
import com.egiwon.snsapp.R
import com.egiwon.snsapp.auth.SignUpFragment
import com.egiwon.snsapp.base.BaseActivity
import com.egiwon.snsapp.databinding.ActivityMainBinding
import com.egiwon.snsapp.tab.TabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        showFragment(TabFragment())
        setupObserve()
    }

    override fun setupObserve() {
        super.setupObserve()
        viewModel.showSignUp.observe(this, EventObserver {
            showFragment(SignUpFragment.newInstance(false), true)
        })

        viewModel.showLogin.observe(this, EventObserver {
            showFragment(SignUpFragment.newInstance(true), true)
        })
    }

    private fun showFragment(fragment: Fragment, isAddStack: Boolean = false) {
        supportFragmentManager.commit(allowStateLoss = true) {
            if (isAddStack) addToBackStack(Fragment::class.java.name)
            replace(R.id.fragment_holder, fragment, Fragment::class.java.name)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 0) super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}