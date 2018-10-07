package com.module.footballclub.ui.matchdetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.module.footballclub.R
import com.module.footballclub.databinding.ActivityMatchDetailBinding
import com.module.footballclub.model.EventsItem

class MatchDetailActivity : AppCompatActivity(), MatchDetailResultCallback {
    lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        val viewModel = ViewModelProviders.of(this,
                MatchDetailViewModelFactory(this))
                .get(MatchDetailViewModel::class.java)
        binding.event = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSuccess() {

    }

    override fun onError() {

    }

    override fun loadEventItem(): EventsItem {
        val footballClub = intent.extras.getParcelable("data") as EventsItem
        return footballClub
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
