package com.dyhdyh.widget.loadingbar2.factory;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyhdyh.widget.loadingbar2.R;

/**
 * @author dengyuhan
 * created 2019/3/14 15:52
 */
public class MaterialViewFactory implements LoadingFactory<ViewGroup, View> {
    private TextView mMessageView;

    @Override
    public View onCreate(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_material_loading, parent, false);
        mMessageView = view.findViewById(android.R.id.message);
        return view;
    }

    @Override
    public void updateStatus(@Nullable Object[] extras) {
        if (extras != null && extras.length > 0 && extras[0] instanceof CharSequence) {
            mMessageView.setVisibility(View.VISIBLE);
            mMessageView.setText((CharSequence) extras[0]);
        } else {
            mMessageView.setVisibility(View.GONE);
        }
    }
}
