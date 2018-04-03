package com.example.mysussrx.adapter.section;

import java.io.Serializable;

/**
 * Created by hzg on 2017/12/25 15:01
 * from: https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * <p>
 * SectionAdapter 的数据类
 */
public abstract class SectionEntity<T> implements Serializable {
    public boolean isHeader;
    public T t;
    public String header;

    public SectionEntity(boolean isHeader, String header) {
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
