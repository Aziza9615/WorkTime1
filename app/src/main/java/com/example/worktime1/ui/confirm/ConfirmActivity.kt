package com.example.worktime1.ui.confirm

import android.content.Intent
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.CodeEvent
import com.example.worktime1.databinding.ActivityConfirmBinding
import com.example.worktime1.ui.company.CompanyActivity
import com.example.worktime1.ui.email.EmailActivity
import com.example.worktime1.ui.scan.ScanActivity
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ConfirmActivity : BaseActivity<ConfirmViewModel, ActivityConfirmBinding>(ConfirmViewModel::class) {

    override fun getViewBinding() = ActivityConfirmBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ConfirmViewModel::class)
        onClick()
        countdown()
        val view = binding.smsCodeView
        view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
            Toast.makeText(this, "$code", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = binding.smsCodeView.toString().trim()
            binding.btnNext.isEnabled = name.isNotEmpty()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun onClick() {
        val code = binding.smsCodeView.toString().trim()
        binding.btnNext.isEnabled = code.isNotEmpty()
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this@ConfirmActivity, CompanyActivity::class.java))
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

    private fun countdown() {
            object : CountDownTimer(53000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.btnSend.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    val code = binding.btnSend.toString().trim()
                    binding.btnSend.isEnabled = code.isNotEmpty()
                    binding.btnSend.text = "Отправить снова 00:53"
                    binding.btnSend.setOnClickListener {
                        startActivity(Intent(this@ConfirmActivity, EmailActivity::class.java))
                    }
                }
            }.start()
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CodeEvent.CodeFetched -> {
                    startActivity(Intent(this, CompanyActivity::class.java))
                    finish()
                }
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.loading.observe(this, Observer {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        })
    }
}

