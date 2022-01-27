package com.example.myapplication23.screen.myinfo.customerservice


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication23.R
import com.example.myapplication23.databinding.ActivityCsBinding
import com.example.myapplication23.screen.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel



/**
 * @author HeeTae Heo(main),
 * Geonwoo Kim, Doyeop Kim, Namjin Jeong, Eunho Bae (sub)
 * @since
 * @throws
 * @description
 */

class CSActivity : BaseActivity<CSViewModel, ActivityCsBinding>() {

    override fun getViewBinding(): ActivityCsBinding
    = ActivityCsBinding.inflate(layoutInflater)

    override val viewModel by viewModel<CSViewModel>(

    )

    override fun observeData() = with(binding) {
             //TODO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


    override fun initViews() = with(binding) {
        showCSCenterFragment(CSCenterFragment.newInstance(),CSCenterFragment.TAG)

    }

        private fun showCSCenterFragment(fragment: Fragment, tag: String) {
                supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, tag).commit()
        }




}



