package com.example.tikiapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.tikiapp.R
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {
    private val mArgs: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: DeailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeailViewModel::class.java)
//        wv_detail.settings.javaScriptEnabled = true;

        wv_detail.loadUrl(mArgs.url)
    }

}