package com.dyhdyh.widget.loadingbar2;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyhdyh.widget.loadingbar2.controller.LoadingController;
import com.dyhdyh.widget.loadingbar2.controller.LoadingDialogController;
import com.dyhdyh.widget.loadingbar2.controller.LoadingViewController;
import com.dyhdyh.widget.loadingbar2.factory.LoadingFactory;
import com.dyhdyh.widget.loadingbar2.factory.MaterialDialogFactory;
import com.dyhdyh.widget.loadingbar2.factory.MaterialViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author dengyuhan
 * created 2019/3/14 11:12
 */
public final class LoadingBar {
    private LoadingFactory mFactory;
    private LoadingController mController;
    private Object[] mExtras;

    public LoadingBar setFactory(LoadingFactory factory) {
        this.mFactory = factory;
        return this;
    }

    public LoadingBar setFactoryFromResource(@LayoutRes int layoutId) {
        if (mController instanceof LoadingViewController) {
            return setFactory(createViewFactoryFromResource(layoutId));
        } else if (mController instanceof LoadingDialogController) {
            return setFactory(createDialogFactoryFromResource(layoutId));
        }
        return this;
    }

    public LoadingBar setFactoryFromView(View view) {
        if (mController instanceof LoadingViewController) {
            return setFactory(createViewFactoryFromView(view));
        } else if (mController instanceof LoadingDialogController) {
            return setFactory(createDialogFactoryFromView(view));
        }
        return this;
    }

    public LoadingBar extras(Object[] extras) {
        this.mExtras = extras;
        return this;
    }

    public void show() {
        mController.show(mFactory, mExtras);
    }

    public void cancel() {
        mController.cancel();

        LoadingBarPool.remove(this);
    }

    public static LoadingBar dialog(Context context) {
        LoadingBar instance = LoadingBarPool.get(context);
        if (instance == null) {
            instance = new LoadingBar();
            instance.mController = new LoadingDialogController(context);
            LoadingBarPool.put(context, instance);
        }
        //如果不是默认的工厂 要默认设一个工厂
        if (!(instance.mFactory instanceof MaterialDialogFactory)) {
            instance.mFactory = new MaterialDialogFactory();
        }
        return instance;
    }

    public static LoadingBar view(View parent) {
        LoadingBar instance = LoadingBarPool.get(parent);
        if (instance == null) {
            instance = new LoadingBar();
            instance.mController = new LoadingViewController(parent);
            LoadingBarPool.put(parent, instance);
        }
        //如果不是默认的工厂 要默认设一个工厂
        if (!(instance.mFactory instanceof MaterialViewFactory)) {
            instance.mFactory = new MaterialViewFactory();
        }
        return instance;
    }

    public static int getPoolCount() {
        return LoadingBarPool.getPool().size();
    }

    public static void release() {
        final List<Integer> recycledKeys = new ArrayList<>();
        final SparseArray<LoadingBar> pool = LoadingBarPool.getPool();
        for (int i = 0; i < pool.size(); i++) {
            int key = pool.keyAt(i);
            if (pool.get(key).mController.isCanRecycled()) {
                recycledKeys.add(key);
            }
        }
        for (Integer key : recycledKeys) {
            pool.get(key).cancel();
        }
    }


    private LoadingFactory<ViewGroup, View> createViewFactoryFromResource(@LayoutRes int layoutId) {
        return new LoadingFactory<ViewGroup, View>() {
            @Override
            public String getKey() {
                return String.format(Locale.getDefault(), "ViewFactoryFromResource@%d", layoutId);
            }

            @Override
            public View onCreate(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            }

            @Override
            public void updateStatus(Object[] extras) {

            }
        };
    }


    private LoadingFactory<Context, Dialog> createDialogFactoryFromView(View view) {
        return new LoadingFactory<Context, Dialog>() {
            @Override
            public String getKey() {
                return String.format(Locale.getDefault(), "DialogFactoryFrom#%s@%d", view.getClass().getName(), view.getId());
            }

            @Override
            public Dialog onCreate(Context params) {
                return new AlertDialog.Builder(params)
                        .setView(view)
                        .setCancelable(false)
                        .create();
            }

            @Override
            public void updateStatus(Object[] extras) {

            }
        };
    }

    private LoadingFactory<ViewGroup, View> createViewFactoryFromView(View view) {
        return new LoadingFactory<ViewGroup, View>() {
            @Override
            public String getKey() {
                return String.format(Locale.getDefault(), "ViewFactoryFrom#%s@%d", view.getClass().getName(), view.getId());
            }

            @Override
            public View onCreate(ViewGroup parent) {
                return view;
            }

            @Override
            public void updateStatus(Object[] extras) {

            }
        };
    }


    private LoadingFactory<Context, Dialog> createDialogFactoryFromResource(@LayoutRes int layoutId) {
        return new LoadingFactory<Context, Dialog>() {
            @Override
            public String getKey() {
                return String.format(Locale.getDefault(), "DialogFactoryFromResource@%d", layoutId);
            }

            @Override
            public Dialog onCreate(Context params) {
                final View view = LayoutInflater.from(params).inflate(layoutId, null);
                return new AlertDialog.Builder(params)
                        .setView(view)
                        .setCancelable(false)
                        .create();
            }

            @Override
            public void updateStatus(Object[] extras) {

            }
        };
    }


}