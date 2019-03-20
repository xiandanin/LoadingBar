package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyhdyh.loadingbar.example.adapter.ExampleModel;
import com.dyhdyh.loadingbar.example.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 多loading的操作
 * author  dengyuhan
 * created 2016/7/27 18:29
 */
public class ListLoadingActivity extends BaseActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loading);
        rv =  this.findViewById(R.id.rv);

        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.addItemDecoration(new DividerGridItemDecoration(this));
        rv.setAdapter(new TextAdapter(getTestData()));
    }

    private List<ExampleModel> getTestData() {
        List<ExampleModel> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add(new ExampleModel("Item " + i,true,new Random().nextInt(2000)));
        }
        return data;
    }

}
