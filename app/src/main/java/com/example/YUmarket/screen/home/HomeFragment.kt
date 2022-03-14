package com.example.YUmarket.screen.home


import androidx.core.view.isVisible
import com.example.YUmarket.databinding.FragmentHomeBinding
import com.example.YUmarket.screen.base.BaseFragment
import com.example.YUmarket.screen.home.homelist.HomeCategory
import com.example.YUmarket.screen.home.homelist.HomeListFragment
import com.example.YUmarket.util.LocationData
import com.example.YUmarket.util.LocationState
import com.example.YUmarket.widget.adapter.HomeListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment
    : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: HomeListFragmentPagerAdapter


    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
    }

    override fun observeData() = with(binding) {
//        initViewPager()
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    initViewPager()
                }
            }
        }
    }

    private fun initViewPager() = with(binding) {
        orderChipGroup.isVisible = true

        if (::viewPagerAdapter.isInitialized.not()) {
            val homeCategory = HomeCategory.values()

            val homeListFragmentList = homeCategory.map {
//                RestaurantListFragment.newInstance(it, locationLatLng)
                HomeListFragment.newInstance(it)
            }
            viewPagerAdapter = HomeListFragmentPagerAdapter(
                this@HomeFragment,
                homeListFragmentList,
//                locationLatLng
            )
            viewPager.adapter = viewPagerAdapter

            viewPager.offscreenPageLimit = homeCategory.size

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setText(HomeCategory.values()[position].categoryNameId)
            }.attach()
        }


    }


    companion object {
        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            return HomeFragment().apply {

            }
        }
    }

    override fun backStack() {
    }
}