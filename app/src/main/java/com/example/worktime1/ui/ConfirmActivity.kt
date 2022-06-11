package com.example.worktime1.ui

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityAuthBinding
import com.example.worktime1.databinding.ActivityConfirmBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ConfirmActivity : BaseActivity<AuthViewModel, ActivityConfirmBinding>(AuthViewModel::class) {

    override fun getViewBinding() = ActivityConfirmBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
        setupListener()
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val view1 = binding.view.text.toString().trim()
            val view2 = binding.view2.text.toString().trim()
            val view3 = binding.view3.text.toString().trim()
            val view4 = binding.view4.text.toString().trim()
            val view5 = binding.view5.text.toString().trim()
            val view6 = binding.view6.text.toString().trim()
            binding.nextBtn.isEnabled = view1.isNotEmpty() && view2.isNotEmpty() && view3.isNotEmpty() && view4.isNotEmpty() && view5.isNotEmpty() && view6.isNotEmpty()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun setupListener() {
        binding.view.addTextChangedListener(loginTextWatcher)
        binding.view2.addTextChangedListener(loginTextWatcher)
        binding.view3.addTextChangedListener(loginTextWatcher)
        binding.view4.addTextChangedListener(loginTextWatcher)
        binding.view5.addTextChangedListener(loginTextWatcher)
        binding.view6.addTextChangedListener(loginTextWatcher)
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this@ConfirmActivity, WebActivity::class.java))
        }
        binding.arrow.setOnClickListener {
            onBackPressed()
        }
        binding.arrowTxt.setOnClickListener {
            onBackPressed()
        }
    }

    override fun subscribeToLiveData() {

    }
}