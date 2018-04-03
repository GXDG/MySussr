package com.example.mysussrx.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by DELL on 2018年1月2日 002.
 * E-Mail:n.zjx@163.com
 * PrivyM8
 * BasePresenter: MVP P层基类
 */

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    protected CompositeDisposable mDisposable;

    /**
     * 绑定view
     * @param view BaseView 子类
     */
    void attach(V view) {
        this.mView = view;
        mDisposable = new CompositeDisposable();
    }

    /**
     * 取消订阅
     */
    void unSubscribe() {
        mDisposable.clear();
        mView = null;
    }

}
