package com.egiwon.snsapp.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.egiwon.snsapp.EventObserver
import com.egiwon.snsapp.R
import com.egiwon.snsapp.auth.SignUpLoginFragment
import com.egiwon.snsapp.base.BaseActivity
import com.egiwon.snsapp.databinding.ActivityMainBinding
import com.egiwon.snsapp.detail.card.CardDetailFragment
import com.egiwon.snsapp.detail.user.UserDetailFragment
import com.egiwon.snsapp.ext.setOnSingleClick
import com.egiwon.snsapp.tab.TabFragment
import com.egiwon.snsapp.tab.home.model.User
import com.egiwon.snsapp.tab.imagefeed.model.Card
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            vm = viewModel
            buttonLogout.setOnSingleClick {
                setButtonVisibleLogout()
            }
        }

        showFragment(TabFragment())
        setupObserve()
    }

    private fun setupObserve() {

        viewModel.showSignUp.observe(this, EventObserver {
            showFragment(SignUpLoginFragment.newInstance(false), true)
        })

        viewModel.showLogin.observe(this, EventObserver {
            showFragment(SignUpLoginFragment.newInstance(true), true)
        })

        viewModel.loginAuth.observe(this, Observer {
            if (it.userId > 0) {
                onPopBackStack()
                binding.setButtonVisibleLogin()
            }
        })

        viewModel.selectedItem.observe(this, EventObserver {
            if (it is User) {
                if (it.id < 0) return@EventObserver
                showFragment(UserDetailFragment.newInstance(it.id), true)
            } else if (it is Card) {
                if (it.id < 0) return@EventObserver
                showFragment(CardDetailFragment.newInstance(it.id), true)
            }
        })
    }

    private fun ActivityMainBinding.setButtonVisibleLogin() {
        buttonLogout.visibility = View.VISIBLE
        buttonLogin.visibility = View.GONE
        buttonSignUp.visibility = View.GONE
    }

    private fun ActivityMainBinding.setButtonVisibleLogout() {
        buttonLogout.visibility = View.GONE
        buttonLogin.visibility = View.VISIBLE
        buttonSignUp.visibility = View.VISIBLE
    }

    private fun showFragment(fragment: Fragment, isAddStack: Boolean = false) {
        supportFragmentManager.commit(allowStateLoss = true) {
            if (isAddStack) addToBackStack(Fragment::class.java.name)
            add(R.id.fragment_holder, fragment, Fragment::class.java.name)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 0) super.onBackPressed()
        onPopBackStack()
    }

    private fun onPopBackStack() {
        supportFragmentManager.popBackStack()
    }
}