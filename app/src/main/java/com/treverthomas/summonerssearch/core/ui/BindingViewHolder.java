package com.treverthomas.summonerssearch.core.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Used to encapsulate a RecyclerView.ViewHolder tied to a ViewDataBinding class.
 *
 * @param <T> A {@link android.databinding.ViewDataBinding} subclass
 */
@SuppressWarnings("unchecked")
public final class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    @SuppressWarnings("WeakerAccess")
    public BindingViewHolder(final T binding) {
        super(binding.getRoot());
    }

    public T getBinding() {
        return (T) DataBindingUtil.bind(itemView);
    }

    public static BindingViewHolder inflate(final Context context, @LayoutRes final int layoutId,
                                            final ViewGroup parent, final boolean attactToRoot) {
        return new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, attactToRoot));
    }
}
