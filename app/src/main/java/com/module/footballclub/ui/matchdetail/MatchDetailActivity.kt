package com.module.footballclub.ui.matchdetail

import android.arch.lifecycle.ViewModelProviders
import android.database.sqlite.SQLiteConstraintException
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.module.footballclub.R
import com.module.footballclub.dao.FavoriteObject
import com.module.footballclub.dao.database
import com.module.footballclub.databinding.ActivityMatchDetailBinding
import com.module.footballclub.model.EventsItem
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class MatchDetailActivity : AppCompatActivity(), MatchDetailResultCallback {
    lateinit var binding: ActivityMatchDetailBinding
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        val viewModel = ViewModelProviders.of(this,
                MatchDetailViewModelProviderFactory(this))
                .get(MatchDetailViewModel::class.java)
        binding.event = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        id = loadEventItem().idEvent
        favoriteState()
    }

    override fun onSuccess() {

    }

    override fun onError() {

    }

    override fun loadEventItem(): EventsItem {
        val footballClub = intent.extras.getParcelable("data") as EventsItem
        return footballClub
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteObject.TABLE_FAVORITE,
                        FavoriteObject.TEAM_ID to loadEventItem().idHomeTeam,
                        FavoriteObject.TEAM_NAME to loadEventItem().strHomeTeam,
                        FavoriteObject.TEAM_BADGE to loadEventItem().strAwayTeam)
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteObject.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to "")
            }
            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val query = select(FavoriteObject.TABLE_FAVORITE).whereArgs("TEAM_ID = {id}", "id" to id!!)
            val result = query.parseList(classParser<FavoriteObject>())
            if (!result.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
