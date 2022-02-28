package com.example.myapplication23.screen.myinfo.customerservice

/**
 * @author HeeTae Heo(main),
 * Geonwoo Kim, Doyeop Kim, Namjin Jeong, Eunho Bae (sub)
 * @since
 * @throws
 * @description
 */

import android.content.Intent
import android.widget.TextView
import androidx.core.view.get
import androidx.navigation.Navigation
import com.example.myapplication23.R
import com.example.myapplication23.databinding.FragmentCsBinding
import com.example.myapplication23.screen.MainActivity
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.screen.myinfo.customerservice.center.CSCenterActivity
import com.example.myapplication23.screen.myinfo.customerservice.email.EmailFragment
import com.example.myapplication23.screen.myinfo.customerservice.list.CSCategory
import com.example.myapplication23.screen.myinfo.customerservice.list.CSListFragment
import com.example.myapplication23.widget.adapter.HomeListFragmentPagerAdapter


import org.koin.android.viewmodel.ext.android.viewModel

class CSFragment : BaseFragment<FragmentCsBinding>() {

    private lateinit var viewAdapter: HomeListFragmentPagerAdapter


    override fun getViewBinding(): FragmentCsBinding =
        FragmentCsBinding.inflate(layoutInflater)

    override fun observeData() {


        //TODO(로그인 확인) ->Success->

    }

    override fun initViews() = with(binding) {
        binding.CSTextView.text = "고객센터"


        if (::viewAdapter.isInitialized.not()) {
            val csCategory = CSCategory.values()

            val csListFragmentList = csCategory.map {
                CSListFragment.newInstance(it)
            }


            viewPagerCs.adapter = HomeListFragmentPagerAdapter(
                this@CSFragment,
                csListFragmentList
            )
            viewPagerCs.offscreenPageLimit = 1
        }

        binding.intentmyinfo.setOnClickListener {
            view?.findViewById<TextView>(R.id.intentmyinfo)!!.setOnClickListener {
                view?.let { it1 ->
                    Navigation.findNavController(it1)
                        .navigate(R.id.action_CSFragment_to_myInfoFragment)
                }

            }
                binding.editBtn.setOnClickListener {
                    view?.findViewById<TextView>(R.id.email_center)!!.setOnClickListener {
                        view?.let { it1 ->
                            Navigation.findNavController(it1)
                                .navigate(R.id.action_CSFragment_to_emailFragment)
                        }
                    }
                }

                binding.back.setOnClickListener {
                    back()
                }



        }
    }

    private fun editEmail(fragment: EmailFragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                EmailFragment.newInstance(),
                EmailFragment.TAG
            )
            .commit()
    }


    private fun showMyinfo(activity: MainActivity) {
        activity?.let {
            var intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun back() {
        view?.findViewById<TextView>(R.id.back)!!.setOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1)
                    .navigate(R.id.action_CSFragment_to_CSCenterFragment)
            }
        }
    }


    companion object {
        const val TAG = "CSFragment"

        fun newInstance(): CSFragment {
            return CSFragment().apply {

            }
        }
    }
}



