package com.example.mysussrx.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mysussrx.utils.Logger;
import com.example.mysussrx.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;



/**
 * Created by DELL on 2018年1月2日 002.
 * E-Mail:n.zjx@163.com
 * PrivyM8
 * BaseActivity
 * 子类不需要Presenter则不用带泛型
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter;
    protected View mView;

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.i("currentActivity:" + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        mView = LayoutInflater.from(this).inflate(getLayoutResId(), null);
        setContentView(mView);
        if (isImmersionBarEnable()) {
            initStatusBar();
        }
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initViews();
        initData();
        initListeners();
    }

    private void initStatusBar() {
//        mImmersionBar = ImmersionBar.with(this)
//                .statusBarColor(R.color.bg_white)
//                .navigationBarColor(R.color.bg_white)
//                .statusBarDarkFont(true)
//                .fitsSystemWindows(true)
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        mImmersionBar.init();
    }

    public void startAct(@NonNull Class<? extends AppCompatActivity> c) {
        startActivity(new Intent(this, c));
    }

    public void startAct(Class<? extends AppCompatActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActForResult(@NonNull Class c, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected <V extends View> V findView(@IdRes int id) {
        return findViewById(id);
    }

    /**
     * @param <T> 如果BaseActivity子类不需要Presenter泛型，
     *            此方法获取ViewDataBinding需要强转或者在子类直接使用DataBindingUtil.bind(mView)
     * @return
     */
    protected <T extends ViewDataBinding> T getDataBinding() {
        return DataBindingUtil.bind(mView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // EventBus.getDefault().register(this);
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i(this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i(this.getClass().getSimpleName());
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    //按返回键结束当前activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

   // @Subscribe
    public void subscribeNothing(Context context) {
        //空方法，基类注册EventBus需要的
    }

    /**
     * 是否要显示状态栏
     *
     * @return
     */
    protected boolean isImmersionBarEnable() {
        return true;
    }

    /**
     * @return bundle
     */
    protected Bundle getExtraBundle() {
        return getIntent().getExtras();
    }

    protected void toastS(@NonNull String info) {
        ToastUtil.toastS(info);
    }

    protected void toastS(@StringRes int info) {
        ToastUtil.toastS(info);
    }

    protected void toastL(@NonNull String info) {
        ToastUtil.toastL(info);
    }

    protected void toastL(@StringRes int info) {
        ToastUtil.toastL(info);
    }

    /**
     * 子类重写此方法初始化presenter
     *
     * @return presenter
     */
    protected P initPresenter() {
        return null;
    }

    protected abstract int getLayoutResId();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract void initListeners();

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
