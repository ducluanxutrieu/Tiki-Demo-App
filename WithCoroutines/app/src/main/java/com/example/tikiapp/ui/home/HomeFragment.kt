package com.example.tikiapp.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tikiapp.R
import com.example.tikiapp.data.home.getDatabase
import com.example.tikiapp.databinding.HomeFragmentBinding
import com.smarteist.autoimageslider.SliderAnimations

class HomeFragment : Fragment(), BannerListener {

    private lateinit var viewModel : HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private val bannerAdapter = BannerAdapter(this)
    private val quickLinkAdapter = QuickLinkAdapter()
    private val flashDealAdapter = FlashDealAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupBinding(inflater, container)
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val database = getDatabase(this.requireContext())
        viewModel = ViewModelProvider(this, HomeViewModelFactory(database)).get(HomeViewModel::class.java)

        setupRecyclerView()
        setPullTwoRefresh()
        listenLiveData()
    }

    private fun listenLiveData() {
        viewModel.listBanner.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                showHideBanner(false)
            }else{
                bannerAdapter.setData(it)
                showHideBanner(true)
            }

            mSwipeRefreshLayout.isRefreshing = false
        })

        viewModel.listQuickLink.observe(viewLifecycleOwner, Observer {
            when {
                it == null -> {
                    hideQuickLink()
                }
                it.isEmpty() -> {
                    showHideQuickLink(false)
                }
                else -> {
                    quickLinkAdapter.setData(it)
                    showHideQuickLink(true)
                }
            }

            mSwipeRefreshLayout.isRefreshing = false
        })

        viewModel.listFlashDeal.observe(viewLifecycleOwner, Observer {
            when {
                it == null -> {
                    hideFlashDeal()
                }
                it.isEmpty() -> {
                    showHideFlashDeal(false)
                }
                else -> {
                    flashDealAdapter.setData(it)
                    showHideFlashDeal(true)
                }
            }

            mSwipeRefreshLayout.isRefreshing = false
        })
    }

    private fun setupRecyclerView() {
        binding.imageSlider.setSliderAdapter(bannerAdapter)
        binding.imageSlider.startAutoCycle()
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)

        //quick link
        val gridLayoutManager = GridLayoutManager(binding.root.context, 2, LinearLayoutManager.HORIZONTAL, false)
        binding.rvQuickLink.layoutManager = gridLayoutManager
        binding.rvQuickLink.adapter = quickLinkAdapter

        //Flash deal
        val linearLayoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFlashDeal.layoutManager = linearLayoutManager
        binding.rvFlashDeal.adapter = flashDealAdapter
    }

    private fun setPullTwoRefresh() {
        mSwipeRefreshLayout = binding.swlHome
        viewModel.getAllData()
        hideAllItems()
        mSwipeRefreshLayout.setOnRefreshListener {
            mSwipeRefreshLayout.isRefreshing = true
            hideAllItems()
            viewModel.getAllData()/* {
                mSwipeRefreshLayout.isRefreshing = false
            }*/
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSwipeRefreshLayout.setColorSchemeColors(
                resources.getColor(R.color.colorPrimary, context?.theme),
                resources.getColor(android.R.color.holo_green_dark, context?.theme),
                resources.getColor(android.R.color.holo_orange_dark, context?.theme),
                resources.getColor(android.R.color.holo_blue_dark, context?.theme)
            )
        }
    }

    private fun hideAllItems(){
//        hideBanner()
        hideQuickLink()
        hideFlashDeal()
    }

/*    private fun hideBanner() {
        binding.clpBanner.visibility = View.GONE
        binding.cvBannerSlider.visibility = View.GONE
    }*/

    private fun hideQuickLink(){
        binding.clpQuickLink.visibility = View.GONE
        binding.rvQuickLink.visibility = View.GONE
    }

    private fun hideFlashDeal(){
        binding.clpFlashDeal.visibility = View.GONE
        binding.vFlashDeal.visibility = View.GONE
        binding.tvTitleFlashDeal.visibility = View.GONE
        binding.rvFlashDeal.visibility = View.GONE
    }

    private fun showHideBanner(isShow: Boolean){
        binding.clpBanner.visibility = if (isShow) View.GONE else View.VISIBLE
        binding.cvBannerSlider.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showHideQuickLink(isShow: Boolean){
        binding.clpQuickLink.visibility = if (isShow) View.GONE else View.VISIBLE
        binding.rvQuickLink.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun showHideFlashDeal(isShow: Boolean){
        binding.clpFlashDeal.visibility = if (isShow) View.GONE else View.VISIBLE
        binding.rvFlashDeal.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.tvTitleFlashDeal.visibility = View.VISIBLE
        binding.vFlashDeal.visibility = View.VISIBLE
    }

    override fun bannerClickedListener(url: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(url)
        binding.root.findNavController().navigate(action)
    }
}