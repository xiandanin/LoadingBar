package com.dyhdyh.widget.loadingbar2.strategy;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * 一直向上寻找 直到找到可覆盖的布局
 *
 * @author dengyuhan
 * created 2019/3/14 11:30
 */
public class CoverParentStrategy implements ParentStrategy {

    @Override
    public @Nullable
    ViewGroup findSuitableParent(@Nullable View parent) {
        //循环往上找到最合适的View
        View suitableParent = parent;
        while (suitableParent != null && !isSuitableParent(suitableParent)) {
            final ViewParent viewParent = suitableParent.getParent();
            suitableParent = viewParent instanceof View ? (View) viewParent : null;
        }
        return (ViewGroup) suitableParent;
    }

    /**
     * 判断是否可覆盖的布局
     *
     * @param parent
     * @return
     */
    protected boolean isSuitableParent(@NonNull View parent) {
        final String className = parent.getClass().getName();
        return parent instanceof FrameLayout || parent instanceof RelativeLayout
                || "android.support.constraint.ConstraintLayout".equals(className)
                || "android.support.v4.widget.DrawerLayout".equals(className)
                || "android.support.design.widget.CoordinatorLayout".equals(className)
                || "android.support.v7.widget.CardView".equals(className);
    }
}
