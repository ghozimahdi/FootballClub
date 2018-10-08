package com.module.footballclub.ui.matchdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class MatchDetailViewModelProviderFactory(private val matchDetailResultCallback: MatchDetailResultCallback) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchDetailViewModel(matchDetailResultCallback) as T
    }
}
