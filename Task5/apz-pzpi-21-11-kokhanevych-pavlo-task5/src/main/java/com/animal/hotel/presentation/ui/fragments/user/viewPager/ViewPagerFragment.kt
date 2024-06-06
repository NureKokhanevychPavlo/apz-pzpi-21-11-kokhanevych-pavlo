package com.animal.hotel.presentation.ui.fragments.user.viewPager


import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentViewpagerBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.viewPager.adapter.ViewPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ViewPagerFragment: BaseFragment<FragmentViewpagerBinding>(FragmentViewpagerBinding::inflate),
    NavigationView.OnNavigationItemSelectedListener{

    private val viewModel: ViewPagerViewModel by viewModels()

    override fun setListeners() {
        super.setListeners()
        binding.viewPager.adapter = ViewPagerAdapter(this)
        addButtonMenuListener()
        binding.navView.setOnClickListener{
            it.visibility = View.GONE
        }
        attachTabLayout()
        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserName()
        viewModel.getUserPhoto()
    }

    override fun onStart() {
        super.onStart()
        setObserverUserName()
        setObserverPhoto()
    }

    private fun setObserverUserName() {
        viewModel.userName.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                val headerView = binding.navView.getHeaderView(0)
                val userNameTextView = headerView.findViewById<TextView>(R.id.bar_name)
                userNameTextView.text = data

            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setObserverPhoto() {
        viewModel.userPhoto.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                val headerView = binding.navView.getHeaderView(0)
                val profileImageView = headerView.findViewById<ImageView>(R.id.bar_photo)
                val userPhotoBitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                profileImageView.setImageBitmap(userPhotoBitmap)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun addButtonMenuListener() {
        with(binding) {
            buttonMenu.setOnClickListener {
                toggleVisibility(navView)
            }
        }
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_account ->  findNavController().navigate(R.id.action_viewPagerFragment_to_profileFragment)
            R.id.nav_logout -> activity?.finish()
            R.id.nav_close -> binding.navView.visibility = View.GONE
        }
        return true
    }

    private fun attachTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (PagerScreens.entries[position]) {
                PagerScreens.CURRENT_RENTING_SCREEN -> getString(R.string.screen_current)
                PagerScreens.LIST_HOTEL_SCREEN -> getString(R.string.screen_list_hotels)
                PagerScreens.LIST_PETS_SCREEN -> getString(R.string.screen_list_pets)
                PagerScreens.HISTORY_SCREEN -> getString(R.string.screen_history_renting)
                PagerScreens.FEEDBACK_SCREEN -> getString(R.string.screen_feedback)
            }
        }.attach()
    }

    enum class PagerScreens {
        CURRENT_RENTING_SCREEN,
        LIST_HOTEL_SCREEN,
        HISTORY_SCREEN,
        LIST_PETS_SCREEN,
        FEEDBACK_SCREEN
    }
}