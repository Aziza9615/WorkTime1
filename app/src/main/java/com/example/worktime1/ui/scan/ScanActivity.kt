package com.example.worktime1.ui.scan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.view.Menu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityScanBinding
import com.example.worktime1.ui.email.EmailViewModel
import com.example.worktime1.ui.main.MainActivity
import com.example.worktime1.ui.company.CompanyActivity
import com.example.worktime1.ui.confirm.ConfirmActivity
import com.example.worktime1.utils.Constants
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScanActivity : BaseActivity<EmailViewModel, ActivityScanBinding>(EmailViewModel::class) {

    private lateinit var codeScanner: CodeScanner

    override fun getViewBinding() = ActivityScanBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = EmailViewModel::class)
        setupListener()
        onClick()
    }

    private fun onClick() {
        binding.txtCompany.setOnClickListener {
            startActivity(Intent(this@ScanActivity, CompanyActivity::class.java))
        }
        binding.btnStatistic.setOnClickListener {
            startActivity(Intent(this@ScanActivity, MainActivity::class.java))
        }
        binding.arrow.setOnClickListener {
            startActivity(Intent(this@ScanActivity, ConfirmActivity::class.java))
            finish()
        }
    }

    private fun setupListener() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            startScanning()
        }
    }

    private fun startScanning() {
        val scannerView: CodeScannerView = binding.scan
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan Result", Toast.LENGTH_SHORT).show()
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan Result: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.scan.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permissions", Toast.LENGTH_SHORT).show()
                startScanning()
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized) {
            codeScanner.releaseResources()
        }
        super.onPause()
    }

     @SuppressLint("MissingSuperCall")
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         val barcode = data?.extras?.getString(Constants.BAR_CODE)

         if (barcode == "") {
             Toast.makeText(this@ScanActivity, Constants.BAR_CODE_NOT_FOUND, Toast.LENGTH_LONG)
                 .show()
         }
     }

    override fun subscribeToLiveData() {}
}