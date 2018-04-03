package com.example.mysussrx.adapter.expand;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.example.mysussrx.base.BaseDataAdapter;
import com.example.mysussrx.base.DataBindVH;

import java.util.List;

/**
 * Created by hzg on 2017/12/25 15:01
 * <p>
 * 头部 底部 空布局 Adapter：
 * Header               Header
 * item
 * item        or      (empty)
 * item
 * Footer               Footer
 */

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public abstract class AHeaderFootEmptyAdapter<T> extends BaseDataAdapter<T> {
    private static final int HEADER_VIEW = 0x1245;
    private final int FOOT_VIEW = 0x123124;
    private static final int EMPTY_VIEW = 0x1234;
    private boolean hasHeader = false;
    private boolean hasFoot = false;
    private boolean hasEmpty = false;


    public AHeaderFootEmptyAdapter(List<T> mData, Context mContext) {
        super(mData, mContext);
    }

    //需要Header 、Foot 、Empty View 的请在子类中使用下列方法开启
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public void setHasEmpty(boolean hasEmpty) {
        this.hasEmpty = hasEmpty;
    }

    public void setHasFoot(boolean hasFoot) {
        this.hasFoot = hasFoot;
    }


    protected int getHeaderCount() {
        return hasHeader ? 1 : 0;
    }

    protected int getFootCount() {
        return hasFoot ? 1 : 0;
    }

    protected int getEmptyCount() {
        return hasEmpty && getDataSize() == 0 ? 1 : 0;
    }

    protected int getHeaderPosition() {
        return 0;
    }

    protected int getEmptyPosition() {
        return getHeaderCount();
    }

    protected int getFootPosition() {
        return getHeaderCount() + getEmptyCount() + getDataSize();
    }

    @Override
    protected int getViewSize() {
        return getEmptyCount() + getHeaderCount() + getDataSize() + getFootCount();
    }

    @Nullable
    @Override
    public T getItem(int position) {
        //  Logger.d(position);
        return super.getItem(position - getHeaderCount());
    }

    @Override
    protected int getDefItemViewType(int position) {
        if (hasHeader && position == getHeaderPosition())
            return HEADER_VIEW;
        if (hasFoot && position == getFootPosition())
            return FOOT_VIEW;
        if (hasEmpty && getDataSize() == 0 && position == getEmptyPosition())
            return EMPTY_VIEW;
        return super.getDefItemViewType(position);
    }

    @Override
    protected int getViewLayoutResId(int viewType) {
        switch (viewType) {
            case HEADER_VIEW:
                return getHeaderLayoutResId();
            case FOOT_VIEW:
                return getFootLayoutResId();
            case EMPTY_VIEW:
                return getEmptyLayoutResId();
            default:
                return getItemLayoutResId();
        }
    }

    @Override
    protected void covert(DataBindVH holder, T item, int position) {
        switch (holder.getItemViewType()) {
            case HEADER_VIEW:
                covertHeader(holder, position);
                break;
            case FOOT_VIEW:
                covertFoot(holder, position);
                break;
            case EMPTY_VIEW:
                covertEmpty(holder, position);
                break;
            default:
                covertItem(holder, item, position);
                break;
        }
    }

    //根据需要实现下列抽象方法，没用到的就空实现
    @LayoutRes
    protected abstract int getHeaderLayoutResId();

    @LayoutRes
    protected abstract int getFootLayoutResId();

    @LayoutRes
    protected abstract int getEmptyLayoutResId();

    @LayoutRes
    protected abstract int getItemLayoutResId();


    protected abstract void covertHeader(DataBindVH holder, int position);

    protected abstract void covertFoot(DataBindVH holder, int position);

    protected abstract void covertEmpty(DataBindVH holder, int position);

    protected abstract void covertItem(DataBindVH holder, T item, int position);

}

