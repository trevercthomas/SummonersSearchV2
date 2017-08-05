package com.treverthomas.summonerssearch.core.ui;

import android.databinding.Observable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

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

    private CompositeDisposable disposables;
    private VM viewModel;

    /**
     * Set this to true if you need to track whether the presenter has loaded.
     * Will be set to false on detach.
     */
    @SuppressWarnings("WeakerAccess")
    protected boolean hasLoaded;


    /**
     * Attach the view model to the presenter.
     * <p/>
     * Runs any code found in the load method. Instantiates the CompositeDisposable field
     * <p/>
     * If overridden, must call super.attach(VM viewModel)
     *
     * @param viewModel An instantiated {@link VM} that the presenter will update
     * @see #load()
     * @see CompositeDisposable
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
     * Null out the view model. Dispose all disposables added in addDisposable.
     * <p/>
     * If overridden, must call super.detach()
     *
     * @see #addDisposable(Disposable)
     * @see CompositeDisposable
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
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }

        this.disposables = new CompositeDisposable();
        load();
    }

    private void onPause() {
        unload();
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
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
     * Adds a disposable to the <code>Composite Disposable</code>. If the composite
     * is disposed (added after detach), this disposable will dispose as well.
     *
     * @param subscription A {@link Disposable}
     * @see CompositeDisposable
     */
    @SuppressWarnings("WeakerAccess")
    public final void addDisposable(final Disposable subscription) {
        disposables.add(subscription);
    }

    /**
     * Removes a disposable from the <code>Composite Disposable</code> and dispose.
     *
     * @param subscription A {@link Disposable}
     * @see CompositeDisposable
     */
    public final void removeDisposable(final Disposable subscription) {
        disposables.remove(subscription);
    }

}
