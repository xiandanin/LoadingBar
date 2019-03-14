package com.dyhdyh.widget.loadingbar2.strategy;

import android.view.View;
import android.view.ViewGroup;

/**
 * 直接使用传进来的父布局
 *
 * @author dengyuhan
 * created 2019/3/14 11:30
 */
public class SimpleParentStrategy implements ParentStrategy {

    @Override
    public ViewGroup findSuitableParent(View parent) {
        return parent instanceof ViewGroup ? (ViewGroup) parent : null;
    }
}
