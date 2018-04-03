package com.example.mysussrx.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by hzg on 2017/12/11 15:32
 * 使用DataBinding的BaseViewHolder
 */

public class DataBindVH extends BaseViewHolder {
    public ViewDataBinding mBinding;

    public DataBindVH(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends ViewDataBinding> T getBinding() {
        return (T) mBinding;
    }
}
