package com.egiwon.snsapp.auth

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.egiwon.snsapp.EventObserver
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentSignupBinding
import com.egiwon.snsapp.ext.setOnSingleClick
import com.egiwon.snsapp.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpLoginFragment : BaseFragment<FragmentSignupBinding, AuthViewModel>(
    R.layout.fragment_signup
) {
    override val viewModel: AuthViewModel by viewModels()

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.setIsLogin(it.getBoolean(IS_LOGIN))
        }

        with(binding) {
            vm = viewModel
            buttonJoin.setOnSingleClick {
                viewModel.requestSignUpOrLogin(
                    nickname = editNickname.text.toString(),
                    introduction = editIntroduction.text.toString(),
                    password = editPassword.text.toString()
                )
            }
        }

        setupObserve()
    }

    override fun setupObserve() {
        super.setupObserve()
        viewModel.isInvalidNickName.observe(viewLifecycleOwner, EventObserver {
            showToast(getString(R.string.error_input_nickname))
        })

        viewModel.isInvalidIntroduction.observe(viewLifecycleOwner, EventObserver {
            showToast(getString(R.string.error_input_introduction))
        })

        viewModel.isInvalidPassword.observe(viewLifecycleOwner, EventObserver {
            showToast(getString(R.string.error_invalid_password))
        })

        viewModel.userAuth.observe(viewLifecycleOwner, Observer {
            sharedViewModel.setLoginAuth(it)
        })

        viewModel.errorToastMessage.observe(viewLifecycleOwner, EventObserver {
            showToast(it)
        })
    }

    companion object {
        private const val IS_LOGIN = "is_login"
        fun newInstance(isLogin: Boolean): SignUpLoginFragment {
            val args = bundleOf(IS_LOGIN to isLogin)

            val fragment = SignUpLoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}