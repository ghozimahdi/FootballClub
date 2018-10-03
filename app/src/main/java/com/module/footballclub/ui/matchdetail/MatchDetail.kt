package com.module.footballclub.ui.matchdetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.module.footballclub.R
import com.module.footballclub.databinding.ActivityMatchDetailBinding
import com.module.footballclub.model.EventsItem

class MatchDetail : AppCompatActivity(), MatchDetailResultCallback {
    lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        var viewModel = ViewModelProviders.of(this,
                MatchDetailViewModelFactory(this))
                .get(MatchDetailViewModel::class.java)
        binding.event = viewModel
    }

    override fun onSuccess() {

    }

    override fun onError() {

    }

    override fun loadEventItem(): EventsItem {
        var footballClub = intent.extras.getParcelable("data") as EventsItem
        return footballClub
    }
}
