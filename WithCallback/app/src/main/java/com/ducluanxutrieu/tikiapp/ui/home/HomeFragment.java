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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ducluanxutrieu.tikiapp.R;
import com.ducluanxutrieu.tikiapp.data.TikiDatabase;
import com.ducluanxutrieu.tikiapp.databinding.HomeFragmentBinding;
import com.smarteist.autoimageslider.SliderAnimations;

public class HomeFragment extends Fragment implements BannerListener {

    private HomeViewModel mViewModel;
    private BannerAdapter mBannerAdapter;
    private QuickLinkAdapter mQuickLinkAdapter;
    private FlashDealAdapter mFlashDealAdapter;
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
        TikiDatabase database = TikiDatabase.getInstance(requireContext());
        mViewModel = new ViewModelProvider(this, new HomeViewModelFactory(database)).get(HomeViewModel.class);

        setupRecyclerView();
        setPullTwoRefresh();
        listenLiveData();
    }

    private void listenLiveData() {
        mViewModel.listBanner.observe(getViewLifecycleOwner(), bannerModels -> {
            if (bannerModels.isEmpty()) {
                showHideBanner(false);
            } else {
                mBannerAdapter.setData(bannerModels);
                showHideBanner(true);
            }

            mSwipeRefresh.setRefreshing(false);
        });

        mViewModel.listQuickLink.observe(getViewLifecycleOwner(), listQuickLink -> {
            if (listQuickLink == null) {
                hideQuickLink();
            } else if (listQuickLink.isEmpty()) {
                showHideQuickLink(false);
            } else {
                mQuickLinkAdapter.setData(listQuickLink);
                showHideQuickLink(true);
            }

            mSwipeRefresh.setRefreshing(false);
        });

        mViewModel.listFlashDeal.observe(getViewLifecycleOwner(), listFlashDeal -> {
            if (listFlashDeal == null) {
                hideFlashDeal();
            } else if (listFlashDeal.isEmpty()) {
                showHideFlashDeal(false);
            } else {
                mFlashDealAdapter.setData(listFlashDeal);
                showHideFlashDeal(true);
            }

            mSwipeRefresh.setRefreshing(false);
        });
    }

    private void setPullTwoRefresh() {
        mSwipeRefresh = mBinding.swlHome;
        mViewModel.getAllData();
        hideAllItems();
        mSwipeRefresh.setOnRefreshListener(() -> {
            mSwipeRefresh.setRefreshing(true);
            hideAllItems();
            mViewModel.getAllData();
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
        mBannerAdapter = new BannerAdapter(this);
        mBinding.imageSlider.setSliderAdapter(mBannerAdapter);
        mBinding.imageSlider.startAutoCycle();
        mBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

        //quick link
        mQuickLinkAdapter = new QuickLinkAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mBinding.getRoot().getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
        mBinding.rvQuickLink.setLayoutManager(gridLayoutManager);
        mBinding.rvQuickLink.setAdapter(mQuickLinkAdapter);

        //Flash deal
        mFlashDealAdapter = new FlashDealAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBinding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.rvFlashDeal.setLayoutManager(linearLayoutManager);
        mBinding.rvFlashDeal.setAdapter(mFlashDealAdapter);
    }

    private void hideAllItems() {
//        hideBanner()
        hideQuickLink();
        hideFlashDeal();
    }

    private void hideFlashDeal() {
        mBinding.clpFlashDeal.setVisibility(View.GONE);
        mBinding.vFlashDeal.setVisibility(View.GONE);
        mBinding.tvTitleFlashDeal.setVisibility(View.GONE);
        mBinding.rvFlashDeal.setVisibility(View.GONE);
    }

    private void hideQuickLink() {
        mBinding.clpQuickLink.setVisibility(View.GONE);
        mBinding.rvQuickLink.setVisibility(View.GONE);
    }

    private void showHideBanner(boolean isShow) {
        mBinding.clpBanner.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mBinding.cvBannerSlider.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void showHideQuickLink(Boolean isShow) {
        mBinding.clpQuickLink.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mBinding.rvQuickLink.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void showHideFlashDeal(Boolean isShow) {
        mBinding.clpFlashDeal.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mBinding.rvFlashDeal.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mBinding.tvTitleFlashDeal.setVisibility(View.VISIBLE);
        mBinding.vFlashDeal.setVisibility(View.VISIBLE);
    }

    @Override
    public void bannerClickedListener(String url) {
        HomeFragmentDirections.ActionHomeFragmentToDetailFragment action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(url);
        NavHostFragment.findNavController(this).navigate(action);
    }
}