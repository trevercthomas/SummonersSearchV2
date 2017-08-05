package com.treverthomas.summonerssearch.profile;

import android.content.Context;
import android.content.ContextWrapper;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.treverthomas.summonerssearch.ProfileViewBinding;
import com.treverthomas.summonerssearch.core.dagger.MainActivityComponent;
import com.treverthomas.summonerssearch.core.dagger.ViewComponentInjector;

import javax.inject.Inject;

/**
 * Reusable Summoner Profile view
 */

public class ProfileView extends RelativeLayout {

    @Inject ProfilePresenter profilePresenter;

    private MainActivityComponent component;
    private ProfileViewModel viewModel;
    private ProfileViewBinding binding;

    public ProfileView(Context context) {
        super(context);
    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        viewModel = new ProfileViewModel();
        binding = DataBindingUtil.bind(this);
        binding.setViewModel(viewModel);
        profilePresenter.attach(viewModel);
    }

    private void inject(final Context context) {
        if (context instanceof ViewComponentInjector) {
            component = ((ViewComponentInjector) context).getBaseComponent();
            component.inject(this);
        } else if (context instanceof ContextWrapper){
            inject(((ContextWrapper) context).getBaseContext());
        }
    }

    public class ProfileViewModel extends BaseObservable {
        public final ObservableField<String> summonerName = new ObservableField<>();
    }
}
