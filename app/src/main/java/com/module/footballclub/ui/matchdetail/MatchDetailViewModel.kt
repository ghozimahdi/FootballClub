package com.module.footballclub.ui.matchdetail

import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable
import com.module.footballclub.model.EventsItem
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.module.footballclub.AppLoader
import com.module.footballclub.BuildConfig
import com.module.footballclub.R
import com.module.footballclub.conn.ApiObserver
import com.module.footballclub.conn.MsgErrorBody
import com.module.footballclub.conn.OnCallBack
import com.module.footballclub.conn.ResponseError
import com.module.footballclub.model.MsgTeamDetail


/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class MatchDetailViewModel(matchDetailResultCallback: MatchDetailResultCallback) : ViewModel() {
    var footballClub: EventsItem = matchDetailResultCallback.loadEventItem()

    val urlImageHome: ObservableField<String> = ObservableField()
    val urlImageAway: ObservableField<String> = ObservableField()
    val paramsHome = HashMap<String, String>()
    val paramsAway = HashMap<String, String>()

    init {
        paramsHome.put("id", footballClub.idHomeTeam.toString())
        paramsAway.put("id", footballClub.idAwayTeam.toString())
        doGetHomeTeamDetail()
        doGetAwayTeamDetail()
    }

    private fun doGetAwayTeamDetail() {
        AppLoader.get(AppLoader.appContext)
                .getApiManager()
                .doGet(BuildConfig.TEAMDETAIL, paramsAway, ApiObserver(object : OnCallBack {
                    override fun succses(response: String) {
                        var msg = Gson().fromJson(response, MsgTeamDetail::class.java)
                        urlImageAway.set(msg.teams?.get(0)?.strTeamLogo!!)
                    }

                    override fun error(errorBody: String, responseError: ResponseError) {
                        var msgError = Gson().fromJson(errorBody, MsgErrorBody::class.java)
                    }

                }))
    }

    private fun doGetHomeTeamDetail() {
        AppLoader.get(AppLoader.appContext)
                .getApiManager()
                .doGet(BuildConfig.TEAMDETAIL, paramsHome, ApiObserver(object : OnCallBack {
                    override fun succses(response: String) {
                        var msg = Gson().fromJson(response, MsgTeamDetail::class.java)
                        urlImageHome.set(msg.teams?.get(0)?.strTeamLogo!!)
                    }

                    override fun error(errorBody: String, responseError: ResponseError) {
                        var msgError = Gson().fromJson(errorBody, MsgErrorBody::class.java)
                    }

                }))
    }
}
