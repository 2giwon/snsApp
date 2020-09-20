package com.egiwon.snsapp.auth

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignupBinding, AuthViewModel>(
    R.layout.fragment_signup
) {
    override val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.setIsLogin(it.getBoolean(IS_LOGIN))
        }

        with(binding) {
            vm = viewModel
        }
    }

    companion object {
        private const val IS_LOGIN = "is_login"
        fun newInstance(isLogin: Boolean): SignUpFragment {
            val args = bundleOf(IS_LOGIN to isLogin)

            val fragment = SignUpFragment()
            fragment.arguments = args
            return fragment
        }
    }
}