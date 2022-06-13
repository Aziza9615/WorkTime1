package com.example.worktime1.ui.main

import androidx.fragment.app.FragmentTransaction
import com.example.worktime1.R
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var adapter: MainViewPagerAdapter

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setupBottomNavigationView()
        setupViewPager()
    }

    private fun setupViewPager() {
        adapter = MainViewPagerAdapter(this)
        adapter.addFragments(MainFragment())
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.isEnabled = false
        binding.viewPager.isUserInputEnabled = false
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.MainFragment -> changeCurrentFragment(0)
            }
            true
        }
    }

    private fun changeCurrentFragment(position: Int) {
        binding.viewPager.setCurrentItem(position, false)
    }

//    private fun setupListener() {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.add(R.id.activity, MainFragment(), "MainFragment").commit()
//    }

    override fun subscribeToLiveData() {}
}