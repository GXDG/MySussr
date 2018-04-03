package com.example.mysussrx.base

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.mysussrx.utils.PermissionUtis


/**
 * Created by hzg on 2017/8/30.
 * Activity基础类
 */

abstract class BaseAct<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected var mPresenter: P? = null
    protected lateinit var mView: View
    abstract fun getLayoutResId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mView = LayoutInflater.from(this).inflate(getLayoutResId(), null)
        setContentView(mView)
        mPresenter = initPresenter()
        mPresenter?.attach(this)
        initViews()
        initData()
        initListeners()
    }


    override fun onStart() {
        super.onStart()
        //检测危险权限
        PermissionUtis.checkPermission(this)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unSubscribe()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === PermissionUtis.REQUEST_PERMISSION_CODE) {
            var isAllGranted = true
            // 判断是否所有的权限都已经授予了
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    break
                }
            }
            if (!isAllGranted) {
                PermissionUtis.openAppDetails(this, "应用需要存储卡权限，请到 “应用信息 -> 权限” 中授予！")
            }
        }
    }

    abstract fun initViews()

    abstract fun initData()

    abstract fun initListeners()

    protected fun initPresenter(): P? {
        return null
    }

    //拓展函数
    open fun Activity.toastL(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    open fun Activity.toastS(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    open fun Activity.startAct(target: Class<*>) {
        val intent = Intent(this, target)
        startActivity(intent)
    }

    open fun Activity.startAct(target: Class<*>, bundle: Bundle) {
        val intent = Intent(this, target)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}