package com.treverthomas.summonerssearch.home;

import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.treverthomas.summonerssearch.HomeViewBinding;
import com.treverthomas.summonerssearch.core.dagger.MainActivityComponent;
import com.treverthomas.summonerssearch.core.dagger.ViewComponentInjector;

import javax.inject.Inject;

/**
 * Created by trever.thomas on 2/18/17.
 */

public class HomeView extends RelativeLayout {

    @Inject HomePresenter homePresenter;

    private MainActivityComponent component;
    private HomeViewModel viewModel;
    private HomeViewBinding binding;

    public HomeView(Context context) {
        super(context);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        inject(getContext());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }

        viewModel = new HomeViewModel();
        binding = DataBindingUtil.bind(this);
        binding.setViewModel(viewModel);
        homePresenter.attach(viewModel);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isInEditMode()) {
            return;
        }
        homePresenter.detach();
    }

    private void inject(final Context context) {
        if (context instanceof ViewComponentInjector) {
            component = ((ViewComponentInjector) context).getBaseComponent();
            component.inject(this);
        } else if (context instanceof ContextWrapper) {
            inject(((ContextWrapper) context).getBaseContext());
        }
    }
}
