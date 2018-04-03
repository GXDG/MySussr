package com.example.mysussrx.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysussrx.utils.Logger

/**
 * Created by hzg on 2018/2/9.
 */
abstract class BaseFrag<P : BasePresenter<BaseView>> : Fragment(), BaseView {
    protected lateinit var mContext: Context
    protected var mView: View? = null
    protected var isCreatedView = false
    protected var isVisibleToUser = false
    protected var isFirstLoad = false
    protected var mPresenter: P? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView != null) {
            mView = inflater.inflate(getLayoutResId(), container, false)
        }
        if (mView?.parent != null) {
            (mView?.parent as ViewGroup)?.removeView(mView)
        }


        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isCreatedView) {
            mPresenter = initPresenter()
            mPresenter?.attach(this)
        }
        initView()
        isCreatedView = true
        initData()
        checkLazyLoadData()
        initListener()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            this.isVisibleToUser = true
            checkLazyLoadData()
        } else {
            this.isVisibleToUser = false
        }
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onDestroy() {
        mPresenter?.unSubscribe()
        super.onDestroy()
    }
    private fun checkLazyLoadData() {

        Logger.i(this.javaClass.simpleName + ": isFirstLoad=" + isFirstLoad + "  isCreateView=" + isCreatedView + "  isVisible=" + isVisibleToUser)
        if (!isCreatedView || !isVisibleToUser || !isFirstLoad) {
            return
        }
        Logger.d("加载数据(" + this.javaClass.simpleName + ")")
        isFirstLoad = false
        lazyLoadData()
    }

    protected fun lazyLoadData() {

    }

    protected fun initPresenter(): P? {
        return null;
    }


    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected fun initView() {}
    protected fun initData() {}
    protected fun initListener() {}
}