package com.egiwon.snsapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.egiwon.snsapp.R
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

        supportFragmentManager.commit(allowStateLoss = true) {
            val fragment = TabFragment()
            replace(R.id.fragment_holder, fragment, TabFragment::class.java.name)
        }
    }


}