package com.ducluanxutrieu.tikiapp.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ducluanxutrieu.tikiapp.R;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.databinding.HomeFragmentBinding;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.List;

public class HomeFragment extends Fragment implements BannerListener {

    private HomeViewModel mViewModel;
    private BannerAdapter mBannerAdapter = new BannerAdapter(this);
    private HomeFragmentBinding mBinding;
    private SwipeRefreshLayout mSwipeRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        setupRecyclerView();
        setPullTwoRefresh();
        listenLiveData();
    }

    private void listenLiveData() {
        mViewModel.listBanner.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public void onChanged(Object object) {
                List<BannerModel> list = (List<BannerModel>) object;
                if (list.isEmpty()){
                    showHideBanner(false);
                }else{
                    mBannerAdapter.setData(list);
                    showHideBanner(true);
                }

                mSwipeRefresh.setRefreshing(false);
            }
        });

//        mViewModel.listQuickLink.observe(viewLifecycleOwner, Observer {
//            when {
//                it == null -> {
//                    hideQuickLink()
//                }
//                it.isEmpty() -> {
//                    showHideQuickLink(false)
//                }
//                else -> {
//                    quickLinkAdapter.setData(it)
//                    showHideQuickLink(true)
//                }
//            }
//
//            mSwipeRefreshLayout.isRefreshing = false
//        })
//
//        mViewModel.listFlashDeal.observe(viewLifecycleOwner, Observer {
//            when {
//                it == null -> {
//                    hideFlashDeal()
//                }
//                it.isEmpty() -> {
//                    showHideFlashDeal(false)
//                }
//                else -> {
//                    flashDealAdapter.setData(it)
//                    showHideFlashDeal(true)
//                }
//            }
//
//            mSwipeRefreshLayout.isRefreshing = false
//        })
    }

    private void setPullTwoRefresh() {
        mSwipeRefresh = mBinding.swlHome;
        mViewModel.getAllData();
        hideAllItems();
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefresh.setRefreshing(true);
                hideAllItems();
                mViewModel.getAllData();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSwipeRefresh.setColorSchemeColors(
                    getResources().getColor(R.color.colorPrimary, requireContext().getTheme()),
                    getResources().getColor(android.R.color.holo_green_dark, requireContext().getTheme()),
                    getResources().getColor(android.R.color.holo_orange_dark, requireContext().getTheme()),
                    getResources().getColor(android.R.color.holo_blue_dark, requireContext().getTheme())
            );
        }
    }

    private void setupRecyclerView() {
        mBinding.imageSlider.setSliderAdapter(mBannerAdapter);
        mBinding.imageSlider.startAutoCycle();
        mBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

//        //quick link
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mBinding.getRoot().getContext(), 2, LinearLayoutManager.HORIZONTAL, false)
//        mBinding.rvQuickLink.setLayoutManager(gridLayoutManager);
//        mBinding.rvQuickLink.setAdapter(); = quickLinkAdapter
//
//        //Flash deal
//        val linearLayoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
//        mBinding.rvFlashDeal.layoutManager = linearLayoutManager
//        mBinding.rvFlashDeal.adapter = flashDealAdapter
    }

    private void hideAllItems() {
//        hideBanner()
        hideQuickLink();
        hideFlashDeal();
    }

    private void hideFlashDeal() {

    }

    private void hideQuickLink() {

    }

    private void showHideBanner(boolean isShow){
        mBinding.clpBanner.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mBinding.cvBannerSlider.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void bannerClickedListener(String url) {

    }
}