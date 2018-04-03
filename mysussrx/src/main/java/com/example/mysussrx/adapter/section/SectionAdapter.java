package com.example.mysussrx.adapter.section;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.example.mysussrx.base.BaseDataAdapter;
import com.example.mysussrx.base.DataBindVH;

import java.util.List;

/**
 * Created by hzg on 2017/12/12 15:56
 * <p>
 * 菜单样式 Adapter：
 * ##title####
 * item
 * item
 * item
 * ##title####
 * item
 * item
 */

public abstract class SectionAdapter<T extends SectionEntity> extends BaseDataAdapter<T> {
    private final int HeaderView = 0x00004444;

    public SectionAdapter(List<T> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    protected void covert(DataBindVH holder, T item, int position) {
        if (holder.getItemViewType() == HeaderView)
            covertHeaderView(holder, item);
        else covertItemView(holder, item);
    }

    @Override
    protected int getDefItemViewType(int position) {
        T item = getItem(position);
        if (item != null) {
            return item.isHeader ? HeaderView : 0;
        } else {
            return 0;
        }
    }


    @Override
    protected int getViewLayoutResId(int viewType) {
        return viewType == HeaderView ? getHeaderViewResId() : getItemViewResId();
    }

    @LayoutRes
    protected abstract int getHeaderViewResId();

    @LayoutRes
    protected abstract int getItemViewResId();

    protected abstract void covertHeaderView(DataBindVH holder, T item);

    protected abstract void covertItemView(DataBindVH holder, T item);
}
