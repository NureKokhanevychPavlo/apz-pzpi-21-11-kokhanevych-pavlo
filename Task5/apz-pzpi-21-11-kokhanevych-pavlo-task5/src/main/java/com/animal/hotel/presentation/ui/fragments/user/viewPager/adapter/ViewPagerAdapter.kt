package com.animal.hotel.presentation.ui.fragments.user.viewPager.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.animal.hotel.presentation.ui.fragments.user.current_list.CurrentRentingFragment
import com.animal.hotel.presentation.ui.fragments.user.feedback.FeedbackFragment
import com.animal.hotel.presentation.ui.fragments.user.history_list.HistoryFragment
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.ListHotelFragment
import com.animal.hotel.presentation.ui.fragments.user.pets_list.ListPetsFragment
import com.animal.hotel.presentation.ui.fragments.user.viewPager.ViewPagerFragment

class ViewPagerAdapter(mainFragment: Fragment): FragmentStateAdapter(mainFragment) {
    override fun getItemCount(): Int = ViewPagerFragment.PagerScreens.entries.size

    override fun createFragment(position: Int): Fragment {
        return when(ViewPagerFragment.PagerScreens.entries[position]) {
            ViewPagerFragment.PagerScreens.CURRENT_RENTING_SCREEN -> CurrentRentingFragment()
            ViewPagerFragment.PagerScreens.LIST_HOTEL_SCREEN -> ListHotelFragment()
            ViewPagerFragment.PagerScreens.LIST_PETS_SCREEN -> ListPetsFragment()
            ViewPagerFragment.PagerScreens.HISTORY_SCREEN -> HistoryFragment()
            ViewPagerFragment.PagerScreens.FEEDBACK_SCREEN -> FeedbackFragment()
        }
    }

}