package kr.ac.jejunu.jnu_tong;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AutoClearedDisposable implements LifecycleObserver {
    private final AppCompatActivity lifeCycleOwner;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public AutoClearedDisposable(AppCompatActivity lifeCycleOwner) {
        this.lifeCycleOwner = lifeCycleOwner;
        compositeDisposable = new CompositeDisposable();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (lifeCycleOwner.isFinishing()) {
            compositeDisposable.clear();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        compositeDisposable.clear();
        lifeCycleOwner.getLifecycle().removeObserver(this);
    }

    public void add(Disposable disposable){
        if (lifeCycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED)){
            compositeDisposable.add(disposable);
        }
    }
}