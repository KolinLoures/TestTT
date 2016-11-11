package com.example.kolin.tutu.presentation.common;

import java.lang.ref.WeakReference;

/**
 * Created by kolin on 10.11.2016.
 */

public class AbstractPresenter<T> {

    private WeakReference<T> weakReference = null;

    protected void attachView(T view){
        weakReference = new WeakReference<T>(view);
    }

    protected void detachView(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected boolean isViewAttache(){
        return weakReference.get() != null && weakReference != null;
    }

    protected T getView(){
        return weakReference != null ? weakReference.get() : null;
    }
}
