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
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysussrx.utils.Logger;
import com.example.mysussrx.utils.ToastUtil;


/**
 * Created by DELL on 2018年1月2日 002.
 * E-Mail:n.zjx@163.com
 * PrivyM8
 * BaseFragment: Fragment基类
 * 子类不需要Presenter则不用带泛型
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P mPresenter;

    protected Context mContext;
    protected View mView;
    protected boolean isCreateView;//是否初始化View完成
    protected boolean isVisible;//是否处于可见状态
    protected boolean isFistLoad = true;//是否第一次加载

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (!isCreateView) {
            mPresenter = initPresenter();
            if (mPresenter != null) {
                mPresenter.attach(this);
            }
            initView();
            isCreateView = true;
            initData();
            checkLazyLoadData();
            initListener();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResId(), container, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
   //     EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
     //   EventBus.getDefault().unregister(this);
    }

    /**
     * 子类重写此方法初始化presenter
     *
     * @return presenter
     */
    protected P initPresenter() {
        return null;
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected void initView() {
    }

    /**
     * @param <T> 如果BaseActivity子类不需要Presenter泛型，
     *            此方法获取ViewDataBinding需要强转或者在子类直接使用DataBindingUtil.bind(mView)
     * @return
     */
    protected <T extends ViewDataBinding> T getDataBinding() {
        return DataBindingUtil.bind(mView);
    }

    /**
     * 延迟加载，页面可见时才加载数据
     */
    private void checkLazyLoadData() {
        Logger.i(this.getClass().getSimpleName() + ": isFirstLoad=" + isFistLoad + "  isCreateView=" + isCreateView + "  isVisible=" + isVisible);

        if (!isFistLoad || !isCreateView || !isVisible) {
            return;
        }
        Logger.d("加载数据(" + this.getClass().getSimpleName() + ")");
        isFistLoad = false;
        lazyLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {//界面对用户可见
            isVisible = true;
            checkLazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    /**
     * 等页面可见时再加载，并且加载一次。如果需要每次页面可见都加载的话，把{isFistLoad 在加载是设置为true}
     */
    protected void lazyLoadData() {

    }

    public abstract int getLayoutResId();

    public void startAct(@NonNull Class c) {
        startActivity(new Intent(getContext(), c));
    }

    public void startActForResult(@NonNull Class c, int requestCode) {
        startActivityForResult(new Intent(getContext(), c), requestCode);
    }

    public void startActForResult(@NonNull Class c, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), c);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public void startAct(Class<? extends AppCompatActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
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

    protected <V extends View> V findView(@IdRes int id) {
        return mView.findViewById(id);
    }

   // @Subscribe
    public void subscribeNothing(Context context) {
        //空方法，基类注册EventBus需要的
    }

}
