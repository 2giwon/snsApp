package com.egiwon.snsapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    protected abstract val viewModel: VM

    protected lateinit var binding: VDB
        private set

    protected val compositeDisposable by lazy(::CompositeDisposable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<VDB>(this, layoutResId).apply {
            lifecycleOwner = this@BaseActivity
        }
        setupObserve()
    }

    protected open fun setupObserve() {
        viewModel.toastMessageLiveData.observe(this, Observer {
            showToast(it)
        })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes textResId: Int) {
        showToast(getString(textResId))
    }
}