package com.example.mysussrx.features

import com.example.mysussrx.R
import com.example.mysussrx.base.BaseAct
import com.example.mysussrx.base.BasePresenter
import com.example.mysussrx.base.BaseView
import com.example.mysussrx.utils.PermissionUtis

/**
 * Created by hzg on 2018/3/8.
 * 启动页
 */
class StartActivity : BaseAct<BasePresenter<BaseView>>() {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_start
    }

    override fun initViews() {

    }

    override fun initData() {

    }

    override fun initListeners() {

    }
    
    fun  checkPermission(){
        PermissionUtis.checkPermission(this)
    }
}