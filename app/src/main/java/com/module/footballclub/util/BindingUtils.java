package com.module.footballclub.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by knalb on 03/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter({"setUpWithViewpager"})
    public static void setUpWithViewpager(final TabLayout tabLayout, ViewPager viewPager) {
        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager,
                                         @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
                if (oldAdapter == null && newAdapter == null) {
                    return;
                }
                Log.i("TAG", "onAdapterChanged");
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
