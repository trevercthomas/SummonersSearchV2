package com.treverthomas.summonerssearch.core.ui;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by trever.thomas on 8/5/17.
 */

public final class UtilBindingAdapter {

    private UtilBindingAdapter() {
    }

    @BindingAdapter({"isGone"})
    public static void isGone(final View view, boolean isGone) {
        if (isGone) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

}
