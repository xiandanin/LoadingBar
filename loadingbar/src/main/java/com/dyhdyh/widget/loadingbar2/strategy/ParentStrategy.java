package com.dyhdyh.widget.loadingbar2.strategy;

import android.view.View;
import android.view.ViewGroup;

/**
 * 选择Parent的策略
 *
 * @author dengyuhan
 * created 2019/3/14 11:28
 */
public interface ParentStrategy {
    ViewGroup findSuitableParent(View parent);
}
