package com.egiwon.snsapp.detail.user

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.egiwon.snsapp.R
import com.egiwon.snsapp.base.BaseFragment
import com.egiwon.snsapp.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(
    R.layout.fragment_user_detail
) {
    override val viewModel: UserDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        arguments?.getInt(USER_ID)?.let {
            viewModel.getUserDetailInfo(it)
        }
    }

    companion object {
        private const val USER_ID = "user_id"

        fun newInstance(id: Int): UserDetailFragment {
            val args = bundleOf(USER_ID to id)

            val fragment = UserDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}