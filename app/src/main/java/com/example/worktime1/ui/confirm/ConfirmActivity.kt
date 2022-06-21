package com.example.worktime1.ui.confirm

import android.content.Intent
import android.widget.Toast
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityConfirmBinding
import com.example.worktime1.ui.EmailViewModel
import com.example.worktime1.ui.WebActivity
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ConfirmActivity : BaseActivity<EmailViewModel, ActivityConfirmBinding>(EmailViewModel::class) {

    override fun getViewBinding() = ActivityConfirmBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = EmailViewModel::class)
        val view = binding.smsCodeView//findViewById<SmsConfirmationView>(R.id.sms_code_view)
        view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
            Toast.makeText(this, "$code", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this@ConfirmActivity, WebActivity::class.java))
        }
        binding.arrowTxt.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.arrow.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    override fun subscribeToLiveData() {
        onClick()
    }
}
