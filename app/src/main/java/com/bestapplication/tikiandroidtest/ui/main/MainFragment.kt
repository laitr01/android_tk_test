package com.bestapplication.tikiandroidtest.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bestapplication.tikiandroidtest.R
import com.bestapplication.tikiandroidtest.Response
import com.bestapplication.tikiandroidtest.model.KeywordTwoLine
import com.bestapplication.tikiandroidtest.ui.hotkeyword.HotKeywordAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var hotKeywordAdapter: HotKeywordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        hotKeywordAdapter = HotKeywordAdapter { keyword ->
            Toast.makeText(
                requireContext(),
                "${keyword.lineOne} ${keyword.lineTwo} is selected....",
                Toast.LENGTH_SHORT
            ).show()
        }

        hotKeyWordCarousel.layoutManager = layoutManager
        hotKeyWordCarousel.adapter = hotKeywordAdapter

        viewModel.dataLive().observe(this, Observer { response ->
            response?.let {
                when (it.status) {
                    Response.Status.LOADING -> bindHotKey(View.GONE, View.VISIBLE, emptyList())
                    Response.Status.SUCCEED -> {
                        it.data?.let { data ->
                            bindHotKey(View.GONE, View.GONE, data)
                        } ?: let { _ ->
                            //empty view will show if no data received
                        }
                    }
                    Response.Status.FAILED -> bindHotKey(View.GONE, View.VISIBLE, emptyList())
                }
            }
        })
    }

    private fun bindHotKey(error: Int, loading: Int, keywords: List<KeywordTwoLine>) {
        progressBar.visibility = loading
        errorView.visibility = error
        hotKeywordAdapter.setList(keywords)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
