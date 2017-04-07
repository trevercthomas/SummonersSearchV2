package com.treverthomas.summonerssearch.core.ui;

import android.databinding.Observable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * This class abstracts handles updating a view model. The presenter is instantiated by a View.
 * <p/>
 * <p/>
 * This allows the presenter to be tested without any dependency on the view (Using a unit test rather
 * than an instrumentation or Robolectric test).
 * <p/>
 * For implementation, see https://github.com/codeprogression/android-mvpb/blob/master/app/src/main/java/com/codeprogression
 * /mvpb/core/BaseBindingPresenter.java
 * <p/>
 * <p/>
 * For an explanation of the MVP-B pattern, see http://www.codeprogression.com/2015/10/30/android-presentation-model/
 *
 * @param <VM> A {@link android.databinding.BaseObservable}
 * @author Richard Cirerol (codeprogression)
 */
public abstract class BaseBindingPresenter<VM extends Observable> {

    private CompositeSubscription subscriptions;
    private VM viewModel;

    /**
     * Set this to true if you need to track whether the presenter has loaded.
     * Will be set to false on detach.
     */
    protected boolean hasLoaded;


    /**
     * Attach the view model to the presenter.
     * <p/>
     * Runs any code found in the load method. Instantiates the CompositeSubscription field
     * <p/>
     * If overridden, must call super.attach(VM viewModel)
     *
     * @param viewModel An instantiated {@link VM} that the presenter will update
     * @see #load()
     * @see CompositeSubscription
     */
    @CallSuper
    public void attach(@NonNull final VM viewModel) {
        if (this.viewModel == null) {
            this.viewModel = viewModel;
        }
        onResume();
    }

    /**
     * Detach the viewmodel and pause.
     * <p/>
     * Null out the view model. Unsubscribes from all subscriptions added in addSubscription.
     * <p/>
     * If overridden, must call super.detach()
     *
     * @see #addSubscription(Subscription)
     * @see Subscription
     */
    @CallSuper
    public void detach() {
        this.viewModel = null;
        onPause();
    }

    /**
     * Check for an existing (non-null) view model.
     *
     * @return true if <code>viewModel</code> is not null
     */
    protected final boolean hasViewModel() {
        return viewModel != null;
    }

    /**
     * Get the currently-attached view model.
     *
     * @return attached view model
     */
    public final VM getViewModel() {
        return viewModel;
    }

    private void onResume() {
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }

        this.subscriptions = new CompositeSubscription();
        load();
    }

    private void onPause() {
        unload();
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }
        hasLoaded = false;
    }


    /**
     * Will run when attached presenter is attached.
     * <p/>
     * This is where you would add subscriptions
     */
    protected abstract void load();

    /**
     * Runs when detached from view model. Add any cleanup code here.
     */
    @SuppressWarnings({"WeakerAccess", "EmptyMethod"})
    protected void unload() {
    }

    /**
     * Adds a subscription to the <code>Composite Subscription</code>. If the composite
     * is unsubscribed (added after detach), this subscription will unsubscribe as well.
     *
     * @param subscription A {@link Subscription}
     * @see CompositeSubscription
     */
    public final void addSubscription(final Subscription subscription) {
        subscriptions.add(subscription);
    }

    /**
     * Removes a subscription from the <code>Composite Subscription</code> and unsubscribes.
     *
     * @param subscription A {@link Subscription}
     * @see CompositeSubscription
     */
    public final void removeSubscription(final Subscription subscription) {
        subscriptions.remove(subscription);
    }

}
