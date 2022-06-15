package com.example.worktime1.ui.confirm

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.worktime1.R
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityConfirmBinding
import com.example.worktime1.ui.AuthViewModel
import com.example.worktime1.ui.WebActivity
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
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
            val view1 = binding.smsCodeView.text.toString().trim()
            binding.nextBtn.isEnabled = view1.isNotEmpty()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun setupListener() {
        binding.smsCodeView.addTextChangedListener(loginTextWatcher)
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this@ConfirmActivity, WebActivity::class.java))
        }
        binding.arrow.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.arrowTxt.setOnClickListener {
            onBackPressed()
            finish()
        }
        val view = findViewById<SmsConfirmationView>(R.id.sms_code_view)
        view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
        }
    }

    override fun subscribeToLiveData() {

    }
}
